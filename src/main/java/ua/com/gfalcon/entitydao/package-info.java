
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

/**
 * Пакет содержит генератор базового CRUD для DAO
 * <br> Использование
 * <br> Каждый домен наследовать от класса Entity (при этом <code>private Long id;</code> - наследуется):
 * <br> <code>@javax.persistence.Entity</code>
 * <br> <code>public class MyClassName extends Entity {</code>
 * <br> <code>...</code>
 * <br> <code>стандартный код класса: перечень полей, конструкторы, геттеры и сеттеры...</code>
 * <br> <code>...</code>
 * <br> <code>}</code>
 * <br>
 * <br> Создаем интерфейс DAO нашего объекта:
 * <br> <code>public interface MyClassNameDao extends EntityDao<MyClassName> {</code>
 * <br> <code>...</code>
 * <br> <code>перечень методов, которые не реализуются шаблонным интерфейсом DAO</code>
 * <br> <code>...</code>
 * <br> <code>}</code>
 * <br>
 * <br> Создаем класс-имплементантацию нашего DAO
 * <br> <code>@org.springframework.stereotype.Repository</code>
 * <br> <code>public class MyClassNameDaoImpl extends GenericEntityDao<MyClassName> implements MyClassNameDao {</code>
 * <br> <code>    protected MyClassNameDaoImpl() {</code>
 * <br> <code>        super(MyClassName.class);</code>
 * <br> <code>    }</code>
 * <br> <code>...</code>
 * <br> <code>код имплементации методов, которые не реализуются шаблонным интерфейсом DAO</code>
 * <br> <code>...</code>
 * <br> <code>}</code>
 *
 * @author Oleksii Khalikov
 * @version v-1.0, 25.02.2016
 * @since v-1.0
 */
package ua.com.gfalcon.entitydao;