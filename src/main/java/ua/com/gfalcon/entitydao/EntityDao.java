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

package ua.com.gfalcon.entitydao;
import com.google.common.base.Optional;

import java.util.List;

/**
 * Base CRUD DAO
 *
 * @param <T> entities
 * @author Oleksii Khalikov
 * @version v-1.0, 25.02.2016
 * @since v-1.0
 */
public interface EntityDao<T extends AbstractEntity> {

    /**
     * Find entity by id
     *
     * @param id
     * @return absent if none found
     */
    Optional<T> findById(Long id);

    /**
     * Find by id or throw exception if none found
     *
     * @param id
     * @return entity
     * @throws EntityNotFoundException if not found
     */
    T findByIdExpected(Long id);

    /**
     * Returns first found entities.
     *
     * @param attribute
     * @param value
     * @return Optional
     * @see EntityDao#findAllBy(String, Object)
     */
    Optional<T> findBy(String attribute, Object value);

    /**
     * Returns first found entity or throw exception is none found.
     *
     * @param attribute
     * @param value
     * @return Optional
     * @throws EntityNotFoundException
     * @see EntityDao#findAllBy(String, Object)
     */
    T findByExpected(String attribute, Object value);

    /**
     * Search for entities which have attributes with a given value
     * Note! nested properties are supported, e.g. foo.bar.pro
     *
     * @param field e.g. "nID", "foo.bar.pro"
     * @param value e.g. "nid123"
     * @return found entities or empty List if nothing found
     */
    List<T> findAllBy(String field, Object value);

    /**
     * Returns all entities from DB.
     *
     * @return empty List if table is empty.
     */
    List<T> findAll();

    /**
     * Find all entities by ids.
     *
     * @param ids
     * @return
     */
    List<T> findAll(List<Long> ids);

    /**
     * @param entity
     * @return updated or created entity
     * @see org.hibernate.Session#saveOrUpdate(Object)
     */
    T saveOrUpdate(T entity);

    /**
     * @param entities
     * @return
     * @see EntityDao#saveOrUpdate(AbstractEntity)
     */
    List<T> saveOrUpdate(List<T> entities);

    /**
     * @param id
     * @see EntityDao#delete(AbstractEntity)
     */
    void delete(Long id);

    /**
     * @param field
     * @param value
     * @return number of deleted entities
     * @see EntityDao#delete(AbstractEntity)
     * @see EntityDao#findBy(String, Object)
     */
    int deleteBy(String field, Object value);

    /**
     * @param entity
     * @throws EntityNotFoundException if entity does not exist
     */
    void delete(T entity);

    /**
     * Delete all given entities
     *
     * @param entities
     * @return not deleted entities
     */
    List<T> delete(List<T> entities);

    /**
     * Delete all entities from DB.
     */
    void deleteAll();

    /**
     * Checks if entity with given id exists
     *
     * @param id
     * @return true - if exists
     */
    boolean exists(Long id);

    /**
     * Returns class of entity
     *
     * @return
     */
    Class<T> getEntityClass();

    /**
     * Search for entities which have attribute in specified values
     *
     * @param field
     * @return
     */
    List<T> findAllByInValues(String field, List<?> value);

}