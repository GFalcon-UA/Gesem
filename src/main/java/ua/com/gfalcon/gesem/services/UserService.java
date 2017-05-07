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
import ua.com.gfalcon.gesem.exeptions.AuthorizationException;
import ua.com.gfalcon.gesem.exeptions.RecordNotFoundException;

import java.util.List;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 * Created by Gesem on 06.01.2017
 */
@Service
@Transactional
public class UserService {
    private static final String UNSUCCESSFUL_REGULAR = "Login or password incorrect";
    private static final String UNSUCCESSFUL_BLOCKED = "Account is blocked. Please, contact with administrator";
    private static final String UNSUCCESSFUL_PASSWORD_EXPIRED = "Password expired";
    private static final String ADMIN_LOGIN = "admin";
    @Autowired
    private UserDAO userDAO;


    public boolean register(String login, String password) throws AuthorizationException {
        User user = new User(login, password);
        try {
            userDAO.save(user);
        } catch (Exception e) {
            throw new AuthorizationException(e.getMessage());
        }
        return true;
    }

    @Transactional(readOnly = true)
    public List<User> getUsersList() {
        return (List<User>) userDAO.findAll();
    }

    public boolean setActivateStatusByUserId(Long userId, Boolean activateStatus) throws RecordNotFoundException {
        User user = getUserById(userId);
        if (user == null) {
            return false;
        }
        user.setEnabled(activateStatus);
        try {
            userDAO.save(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional(readOnly = true)
    public User getUserById(Long userId) throws RecordNotFoundException {
        User user = null;
        if (userDAO.exists(userId)) {
            user = userDAO.findOne(userId);
        } else {
            throw new RecordNotFoundException(String.format("User [id=%s] not found", userId));
        }
        return user;
    }

    public void deleteUserById(Long userId) {
        userDAO.delete(userId);
    }

    public User updateUser(User user) {
        return userDAO.save(user);
    }

}
