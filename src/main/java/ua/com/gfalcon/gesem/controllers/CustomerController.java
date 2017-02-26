package ua.com.gfalcon.gesem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.gfalcon.gesem.services.CustomerService;
import ua.com.gfalcon.utils.JsonRestUtils;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/cms")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "customers", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity getCustomers() {
        return JsonRestUtils.toJsonResponse(HttpStatus.OK, true);
    }

}
