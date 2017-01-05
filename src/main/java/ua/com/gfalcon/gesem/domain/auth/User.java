/*
 * Copyright (c) 2016-2017 by Oleksii KHALIKOV.
 * ========================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ua.com.gfalcon.gesem.domain.auth;

import org.apache.commons.codec.language.Caverphone2;
import org.joda.time.DateTime;
import ua.com.gfalcon.entitydao.AbstractEntity;
import ua.com.gfalcon.gesem.domain.Config;

import javax.persistence.*;
import java.util.Date;

/**
 * Пользователи
 * Created by Gesem
 *
 * @author Oleksii Khalikov
 * @version v-1.0
 * @since 1.0
 */
@Entity(name = "User")
@Table(name = "USERS")
public class User extends AbstractEntity {

    @Column(name = "LOGIN", nullable = false, unique = true)
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "AMOUNT_PASS_FAIL")
    private Integer amountPasswordFailed;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_DATE_FAILED_PASSWORD")
    private Date lastDatePasswordFailed;

    @Column(name = "ACTIATED")
    private boolean activated;

    @Column(name = "ADMINISTRATOR")
    private boolean administrator;

    @Temporal(TemporalType.DATE)
    @Column(name = "REGISTERED")
    private Date registerDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "EXPIRE")
    private Date expireDate;

    protected User() {
        this(("Log" + DateTime.now().toString()), "");
    }

    /**
     * Создать пользователя
     *
     * @param login    логин
     * @param password пароль
     */
    public User(String login, String password) {
        this.login = login;
        setPassword(password);
        registerDate = DateTime.now().toDate();
        DateTime dtRegister = new DateTime(registerDate);
        DateTime dtExpire = dtRegister.plusWeeks(Config.WEEKS_YO_EXPIRE_DATE_ACTIVE_USER);
        expireDate = dtExpire.toDate();
        administrator = false;
        activated = false;
        lastDatePasswordFailed = registerDate;
        amountPasswordFailed = Config.MAX_PASSWORD_FAILED;
    }

    /**
     * Получить логин     *
     *
     * @return логин
     */
    public String getLogin() {
        return login;
    }

    /**
     * Задать логин
     *
     * @param login логин
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Проверить пароль
     *
     * @param string строка для проверки пароля
     * @return boolean
     */
    public boolean checkPassword(String string) {
        Caverphone2 caver = new Caverphone2();
        return password.equals(caver.encode(string));
    }

    /**
     * Задать пароль
     *
     * @param password пароль
     */
    public void setPassword(String password) {
        Caverphone2 caver = new Caverphone2();
        this.password = caver.encode(password);
    }

    /**
     * Получить количество оставшихся доступных ошибок при вводе пароля
     *
     * @return количество оставшихся доступных ошибок при вводе пароля
     */
    public Integer getAmountPasswordFailed() {
        return amountPasswordFailed;
    }

    /**
     * Задать количество оставшихся доступных ошибок при вводе пароля
     *
     * @param amountPasswordFailed количество оставшихся доступных ошибок при вводе пароля
     */
    public void setAmountPasswordFailed(Integer amountPasswordFailed) {
        this.amountPasswordFailed = amountPasswordFailed;
    }

    /**
     * Получить дату последней ошибки при вводе пароля
     *
     * @return Дата последней ошибки при вводе пароля
     */
    public DateTime getLastDatePasswordFailed() {
        return new DateTime(lastDatePasswordFailed);
    }

    /**
     * Задать дату последней ошибки при вводе пароля
     *
     * @param lastDatePasswordFailed Дата последней ошибки при вводе пароля
     */
    public void setLastDatePasswordFailed(DateTime lastDatePasswordFailed) {
        this.lastDatePasswordFailed = lastDatePasswordFailed.toDate();
    }

    /**
     * Проверить активнрован ли аккаунт пользователя или нет
     *
     * @return activated
     */
    public boolean isActivated() {
        return activated;
    }

    /**
     * Задать активацию аккаунта пользователя
     *
     * @param activated boolean
     */
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    /**
     * Проверить, является ли пользователь администратором
     *
     * @return administrator
     */
    public boolean isAdministrator() {
        return administrator;
    }

    /**
     * Установить/снять права администратора
     *
     * @param administrator boolean
     */
    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    /**
     * Получить дату регистрации пользователя
     *
     * @return дата регистрации пользователя
     */
    public DateTime getRegisterDate() {
        return new DateTime(registerDate);
    }

    /**
     * Задать дату регистрации пользователя
     *
     * @param registerDate дата регистрации пользователя
     */
    public void setRegisterDate(DateTime registerDate) {
        this.registerDate = registerDate.toDate();
    }

    /**
     * Получить дату истечения активации аккаунта пользователя
     *
     * @return дата истечения активации аккаунта пользователя
     */
    public DateTime getExpireDate() {
        return new DateTime(expireDate);
    }

    /**
     * Задать дату истечения активации аккаунта пользователя
     *
     * @param expireDate дата истечения активации аккаунта пользователя
     */
    public void setExpireDate(DateTime expireDate) {
        this.expireDate = expireDate.toDate();
    }
}
