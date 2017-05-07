package ua.com.gfalcon.gesem.controllers;

import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.gfalcon.gesem.domain.auth.User;
import ua.com.gfalcon.gesem.exeptions.RecordNotFoundException;
import ua.com.gfalcon.gesem.services.UserService;
import ua.com.gfalcon.utils.JsonRestUtils;

import java.util.Map;

/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller
@RequestMapping(value = "api/users")
public class UsersController {
    @Autowired
    private UserService userService;

    /*@RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity getUsers() {
        List<User> users = userService.getUsersList();
        User admin = userService.getMainAdminUser();
        users.remove(admin);
        return JsonRestUtils.toJsonResponse(users);
    }*/

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity deleteUserById(@RequestParam(name = "nUserId") Long nUserId) {
        try {
            userService.deleteUserById(nUserId);
            return JsonRestUtils.toJsonResponse(true);
        } catch (Exception e) {
            return JsonRestUtils.toJsonResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity updateUser(@RequestParam(name = "nUserId") Long userId,
                                                   @RequestBody String body) {
        if (userId <= 0) {
            return JsonRestUtils.toJsonResponse(HttpStatus.BAD_REQUEST, "nUserId is wrong");
        }
        Map<String, Object> userParams;
        userParams = (Map<String, Object>) JSONValue.parse(body);
        User oldUser = null;
        try {
            oldUser = userService.getUserById(userId);
            if (userParams.containsKey("bActivated")) {
                boolean value = (boolean) userParams.get("bActivated");
                oldUser.setEnabled(value);
            }
            if (userParams.containsKey("bAdministrator")) {
                boolean value = (boolean) userParams.get("bAdministrator");
                /*if (value) {
                    oldUser.addAuthority(Role.ADMIN);
                } else if (oldUser.getAuthorities().contains(Role.ADMIN)) {
                    oldUser.removeAuthority(Role.ADMIN);
                }*/

            }
            if (userParams.containsKey("sPassword")) {
                String value = (String) userParams.get("sPassword");
                oldUser.setPassword(value);
            }
        } catch (RecordNotFoundException e) {
            return JsonRestUtils.toJsonResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        User newUser = userService.updateUser(oldUser);
        return JsonRestUtils.toJsonResponse(newUser);
    }

}
