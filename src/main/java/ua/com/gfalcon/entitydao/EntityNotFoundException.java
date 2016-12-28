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

import org.springframework.dao.EmptyResultDataAccessException;

/**
 * Thrown when entity with given id does not exist.
 *
 * @author Oleksii Khalikov
 * @version v-1.0, 25.02.2016
 * @see EntityDao#delete(AbstractEntity)
 * @see EntityDao#findByIdExpected(Long)
 * @since v-1.0
 */
public class EntityNotFoundException extends EmptyResultDataAccessException {
    public EntityNotFoundException(Long id) {
        this(String.format("Entity with id=%s does not exist", id));
    }

    public EntityNotFoundException(String message) {
        super(message, 1);
    }

    public EntityNotFoundException(String message, Throwable ex) {
        super(message, 1, ex);
    }

    public EntityNotFoundException(Long id, Throwable ex) {
        this(String.format("Entity with id=%s does not exist", id), ex);
    }
}
