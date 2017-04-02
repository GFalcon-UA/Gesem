package ua.com.gfalcon.gesem.controllers;

import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.gfalcon.gesem.domain.auth.User;
import ua.com.gfalcon.gesem.services.AuthService;

import java.util.List;

/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller
@RequestMapping(value = "/users")
public class UsersController {
    @Autowired
    private AuthService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody String getUsers() {
        List<User> users = userService.getUsersList();
        User admin = userService.getMainAdminUser();
        users.remove(admin);
        return JSONValue.toJSONString(users);
    }


}
