package jumawan.com.springbootproject.oauth2_springboot.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping
    public String profile(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal != null) {
            String name = principal.getAttribute("name");
            String email = principal.getAttribute("email");
            String picture = principal.getAttribute("picture"); // Google
            if (picture == null) {
                picture = principal.getAttribute("avatar_url"); // GitHub
            }

            model.addAttribute("name", name);
            model.addAttribute("email", email);
            model.addAttribute("picture", picture);
        } else {
            model.addAttribute("error", "User not authenticated");
        }
        return "profile";
    }

    @PostMapping
    public String updateProfile(HttpServletRequest request, Model model, @AuthenticationPrincipal OAuth2User principal) {
        String displayName = request.getParameter("displayName");
        String bio = request.getParameter("bio");

        model.addAttribute("displayName", displayName);
        model.addAttribute("bio", bio);
        model.addAttribute("success", true);


        if (principal != null) {
            model.addAttribute("name", principal.getAttribute("name"));
            model.addAttribute("email", principal.getAttribute("email"));
        }

        return "profile";
    }
}
