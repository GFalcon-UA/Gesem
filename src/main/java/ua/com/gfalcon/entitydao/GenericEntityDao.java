/*
 * Copyright (c) 2016 by Oleksii V. KHALIKOV
 * ========================================================
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package ua.gfalcon.skripka.entitydao;

import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.in;

/**
 * Base implementation of CRUD operations.
 *
 * @param <T> entities type
 * @author Oleksii Khalikov
 * @version v-1.0, 25.02.2016
 * @see EntityDao
 * @since v-1.0
 */
public class GenericEntityDao<T extends AbstractEntity> implements EntityDao<T> {
    private static final Logger LOG = LogManager.getLogger();

    private final static int DEFAULT_DELETE_BATCH_SIZE = 1000;

    private Class<T> entityClass;
    private SessionFactory sessionFactory;

    protected GenericEntityDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }

    protected Criteria createCriteria() {
        return getSession().createCriteria(entityClass);
    }

    protected String getEntityName() {
        return entityClass.getSimpleName();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Optional<T> findById(Long id) {
        Assert.notNull(id);

        T found = getSession().get(entityClass, id);
        return found == null ? Optional.<T>absent() : Optional.of(found);
    }

    @Override
    public T findByIdExpected(Long id) {
        Optional<T> entity = findById(id);

        if (!entity.isPresent()) {
            throw new EntityNotFoundException(id);
        }

        return entity.get();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Optional<T> findBy(String field, Object value) {
        List<T> allBy = findAllByAttributeCriteria(field, value)
                .setFirstResult(0)
                .list();
        T foundEntity = Iterables.getFirst(allBy, null);
        return foundEntity == null ? Optional.<T>absent() : Optional.of(foundEntity);
    }

    @Override
    public T findByExpected(String attribute, Object value) {
        Optional<T> foundEntity = findBy(attribute, value);
        if (!foundEntity.isPresent()) {
            throw new EntityNotFoundException(String.format("Entity with %s='%s' not found", attribute, value));
        }
        return foundEntity.get();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAllBy(String field, Object value) {
        if (value instanceof Iterable) {
            return findAllByAttributeCriteria(field, (Collection<Object>) value)
                    .list();
        } else {
            return findAllByAttributeCriteria(field, value)
                    .list();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAllByInValues(String field, List<?> value) {
        return findAllByAttributeCriteria(field, (Collection<Object>) value).list();
    }

    protected Criteria findAllByAttributeCriteria(String field, Object value) {
        Assert.hasText(field, "Specify field name");
        Assert.notNull(value, "Specify value");

        Criteria criteria = createCriteria();
        String fieldName = field;

        if (StringUtils.contains(field, ".")) {
            String propertyPath = StringUtils.substringBeforeLast(field, ".");
            fieldName = StringUtils.substringAfterLast(field, ".");

            criteria = criteria.createCriteria(propertyPath);
        }

        return criteria
                .add(eq(fieldName, value));
    }

    protected Criteria findAllByAttributeCriteria(String field, Collection<Object> values) {
        Assert.hasText(field, "Specify field name");
        Assert.notNull(values, "Specify value");

        Criteria criteria = createCriteria();
        String fieldName = field;

        if (StringUtils.contains(field, ".")) {
            String propertyPath = StringUtils.substringBeforeLast(field, ".");
            fieldName = StringUtils.substringAfterLast(field, ".");

            criteria = criteria.createCriteria(propertyPath);
        }
        return criteria.add(in(fieldName, values));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {
        return createCriteria().list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll(List<Long> ids) {
        Assert.notEmpty(ids);

        return createCriteria()
                .add(in("id", ids))
                .list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T saveOrUpdate(T entity) {
        Assert.notNull(entity);

        getSession().saveOrUpdate(entity);
        return entity;
    }

    @Override
    public List<T> saveOrUpdate(List<T> entities) {
        for (T entity : entities) {
            saveOrUpdate(entity);
        }
        return entities;
    }

    @Override
    public void delete(Long id) {
        T entity = findByIdExpected(id);
        delete(entity);
    }

    /**
     * Note: this method actually executes 2 sql requests: select and delete.
     *
     * @param field
     * @param value
     * @return
     */
    @Override
    public int deleteBy(String field, Object value) {
        Assert.hasText(field);
        Assert.notNull(value);

        List<T> toDelete = findAllBy(field, value);

        List<T> notDeleted = delete(toDelete);
        return toDelete.size() - notDeleted.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(T entity) {
        Assert.notNull(entity);

        if (!exists(entity.getId())) {
            throw new EntityNotFoundException(entity.getId());
        }
        T merged = (T) getSession().merge(entity);
        getSession().delete(merged);
    }

    @Override
    public List<T> delete(List<T> entities) {
        List<T> notDeletedEntities = Lists.newArrayList();

        int i = 0;
        for (T entity : entities) {
            if (!exists(entity.getId())) {
                LOG.debug(String.format("Entity %s with id=%s does not exist.", entityClass.getName(), entity.getId()));
                LOG.debug(String.format("Add entity %s with id=%s to not deleted.", entityClass.getName(),
                        entity.getId()));
                notDeletedEntities.add(entity);
            } else {
                LOG.debug(String.format("Delete entity %s with id=%s", entityClass.getName(), entity.getId()));
                delete(entity);
            }
            i++;

            if (i >= DEFAULT_DELETE_BATCH_SIZE) {
                getSession().flush();
                i = 0;
            }
        }
        return notDeletedEntities;
    }

    @Override
    public void deleteAll() {
        getSession().createQuery(String.format("delete from %s", getEntityName()))
                .executeUpdate();
    }

    @Override
    public boolean exists(Long id) {
        return findById(id).isPresent();
    }
}