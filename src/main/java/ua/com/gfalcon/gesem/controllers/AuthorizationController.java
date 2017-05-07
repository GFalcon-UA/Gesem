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

package ua.com.gfalcon.gesem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.gfalcon.gesem.services.UserService;
import ua.com.gfalcon.utils.JsonRestUtils;

import javax.servlet.http.HttpSession;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 * Created by Gesem on 06.01.2017
 */
@Controller
@RequestMapping(value = "api/auth")
@SessionAttributes("loginId")
public class AuthorizationController {
    @Autowired
    private UserService authorizationService;

    /*@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity authenticate(@RequestParam(name = "sLogin") String login, @RequestBody String password,
            Model model) {
        boolean response;
        try {
            response = authorizationService.authenticate(login, password);
        } catch (AuthenticationException exception) {
            return JsonRestUtils.toJsonResponse(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        } catch (Exception exception) {
            return JsonRestUtils.toJsonErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
        if (response) {
            model.addAttribute("loginId", login);
            Map<String, Object> user = null;
            try {
                user = authorizationService.getUserInfoByLogin(login);
            } catch (RecordNotFoundException e) {
                return JsonRestUtils.toJsonErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
            return JsonRestUtils.toJsonResponse(HttpStatus.OK, user);
        } else {
            return JsonRestUtils.toJsonResponse(HttpStatus.LOCKED, false);
        }
    }*/

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity register(@RequestParam(name = "sLogin") String login, @RequestBody String password,
            Model model) {
        System.out.println("in Register 2");
        try {
            authorizationService.register(login, password);
            System.out.println("no exception");
            return JsonRestUtils.toJsonResponse(HttpStatus.OK, true);
        } catch (Exception exception) {
            System.out.println("exception");
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            return JsonRestUtils.toJsonErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    /*@RequestMapping(value = "/checkLogin", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity checkLogin(@RequestParam String login) {
        System.out.println("inCheckLogin");
        if (authorizationService.isLoginUnique(login)) {
            return JsonRestUtils.toJsonResponse(HttpStatus.ACCEPTED, true);
        } else {
            return JsonRestUtils.toJsonResponse(HttpStatus.NOT_ACCEPTABLE, false);
        }
    }*/

    @RequestMapping(value = "/open", method = RequestMethod.GET)
    public boolean gotoMain(HttpSession session) {
        return isAuth(session);
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity ping() {
        return JsonRestUtils.toJsonResponse(true);
    }

    private boolean isAuth(HttpSession session) {
        return session.getAttribute("loginId") != null;
    }
}
