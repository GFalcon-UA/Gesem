package ua.com.gfalcon.gesem.controllers;

import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.gfalcon.gesem.domain.cms.Partner;
import ua.com.gfalcon.gesem.services.CustomerService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "api/cms")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public @ResponseBody String getCustomers(@RequestParam(name = "nID", value = "false") Long nID) {
        List<Partner> result = null;
        if (nID == null) {
            result = customerService.getCustomersList();
        } else {
            Partner partner = null;
            try {
                partner = customerService.getCustomerById(nID);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            if (partner != null) {
                result = new ArrayList<>();
                result.add(partner);
            }
        }

        return JSONValue.toJSONString(result);
    }

    @RequestMapping(value = "/create-customer", method = RequestMethod.POST)
    public void createCustomer(@RequestBody String body) {
        Partner newPartner = null;
        try {
            newPartner = (Partner) new JSONParser().parse(body);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (newPartner != null) {
            customerService.updatePartner(newPartner);
        }
    }

}
