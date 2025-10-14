package jumawan.com.springbootproject.oauth2_springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/logout-success")
    public String logoutPage() {
        return "logout";
    }
}
