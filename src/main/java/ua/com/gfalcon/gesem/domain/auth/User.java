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

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.springframework.security.core.userdetails.UserDetails;
import ua.com.gfalcon.entitydao.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
public class User extends AbstractEntity implements UserDetails {

    @JsonProperty(value = "sLogin")
    @Column(nullable = false, unique = true)
    private String username;

    @JsonProperty(value = "sPassword")
    private String password;

    @JsonProperty(value = "aRoles")
    @Enumerated(EnumType.STRING)
    private Set<Role> authorities;

    @JsonProperty(value = "bEnabled")
    private boolean enabled;

    @JsonProperty(value = "bAccountNonExpired")
    private boolean accountNonExpired;

    @JsonProperty(value = "bAccountNonLocked")
    private boolean accountNonLocked;

    @JsonProperty(value = "bCredentialsNonExpired")
    private boolean credentialsNonExpired;

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
        this(login, password, new HashSet<>(), false);
    }

    public User(String username, String password, Set<Role> authorities, boolean enabled) {
        setUsername(username);
        setPassword(password);
        setAuthorities(authorities);
        setEnabled(enabled);
        setAccountNonLocked(true);
        setAccountNonExpired(true);
        setCredentialsNonExpired(true);
    }

    @Override
    public Set<Role> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(Role authority) {
        authorities.add(authority);
    }

    public void removeAuthority(Role authority) {
        authorities.remove(authority);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
