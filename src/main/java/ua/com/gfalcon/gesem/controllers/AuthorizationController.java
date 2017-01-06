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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.gfalcon.gesem.services.AuthService;

import javax.servlet.http.HttpSession;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 * Created by Gesem on 06.01.2017
 */
@Controller
@RequestMapping(value = "/auth")
@SessionAttributes("loginId")
public class AuthorizationController {
    @Autowired
    private AuthService authorizationService;

    @RequestMapping(value = "authenticate", method = RequestMethod.POST)
    public
    @ResponseBody
    String authenticate(@RequestParam String login, @RequestParam String password,
            Model model) {
        boolean response;
        try {
            response = authorizationService.authenticate(login, password);
        } catch (Exception exception) {
            return "0";
        }
        if (response) {
            model.addAttribute("loginId", login);
            return "1";
        } else {
            return "-1";
        }
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public
    @ResponseBody
    String register(@RequestParam String login, @RequestParam String password,
            Model model) {
        System.out.println("in Register 2");
        try {
            authorizationService.register(login, password);
            System.out.println("no exception");
            return "1";
        } catch (Exception exception) {
            System.out.println("exception");
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            return "0";
        }
    }

    @RequestMapping(value = "checkLogin", method = RequestMethod.GET)
    public
    @ResponseBody
    String checkLogin(@RequestParam String login) {
        System.out.println("inCheckLogin");
        return authorizationService.isLoginUnique(login) ? "1" : "0";
    }

    @RequestMapping(value = "open", method = RequestMethod.GET)
    public String gotoMain(HttpSession session) {
        if (!isAuth(session)) {
            return "login";
        }
        return "main";
    }

    private boolean isAuth(HttpSession session) {
        return session.getAttribute("loginId") != null;
    }
}
