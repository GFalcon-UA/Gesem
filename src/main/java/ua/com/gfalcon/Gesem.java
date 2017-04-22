package ua.com.gfalcon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller
@SpringBootApplication(scanBasePackages = {"ua.com.gfalcon"})
public class Gesem {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Gesem.class, args);
    }

    @RequestMapping("/")
    public String getIndexPage() {
        return "index";
    }

}
