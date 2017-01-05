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

package ua.com.gfalcon.gesem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.gfalcon.gesem.dao.auth.UserDAO;
import ua.com.gfalcon.gesem.domain.auth.User;
import ua.com.gfalcon.gesem.exeptions.AuthenticationException;
import ua.com.gfalcon.gesem.exeptions.AuthorizationException;

import java.util.List;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 * Created by Gesem on 06.01.2017
 */
@Service
@Transactional
public class AuthService {
    private static final String UNSUCCESSFUL_REGULAR = "Login or password incorrect";
    private static final String UNSUCCESSFUL_BLOCKED = "Account is blocked. Please, contact with administrator";
    private static final String UNSUCCESSFUL_PASSWORD_EXPIRED = "Password expired";
    @Autowired
    private UserDAO userDAO;

    private void chekInitDB() {
        List<User> users;
        try {
            users = userDAO.findAll();
            if (users.isEmpty()) {
                init();
            }
        } catch (NullPointerException e) {
            init();
        }
    }

    private void init() {
        User admin = new User("admin", "secret");
        admin.setActivated(true);
        admin.setAdministrator(true);
        admin.setAmountPasswordFailed(1000000);
        admin.setExpireDate(admin.getExpireDate().plusYears(100));
        userDAO.saveOrUpdate(admin);
    }

    public boolean authenticate(String login, String pass) throws AuthenticationException {
        chekInitDB();
        User user;
        List<User> candidate = userDAO.findAllBy("login", login);
        if (candidate.isEmpty() || candidate.size() == 0) {
            String massege = String.format("Login %s not faund", login);
            throw new AuthenticationException(massege);
        }
        for (User us : candidate) {
            if (us.checkPassword(pass)) {
                if (us.isActivated()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        String message = String.format("User %s incorrect password", login);
        throw new AuthenticationException(message);
    }

    public boolean register(String login, String password) throws AuthorizationException {
        chekInitDB();
        User user = new User(login, password);
        try {
            userDAO.saveOrUpdate(user);
        } catch (Exception e) {
            throw new AuthorizationException(e.getMessage());
        }
        return true;
    }

    @Transactional(readOnly = true)
    public boolean isLoginUnique(String login) {
        chekInitDB();
        return userDAO.findAllBy("login", login).size() <= 0;
    }
}
