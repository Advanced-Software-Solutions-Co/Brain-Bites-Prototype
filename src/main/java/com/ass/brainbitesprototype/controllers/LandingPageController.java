package com.ass.brainbitesprototype.controllers;

import com.ass.brainbitesprototype.dtos.RegistrationDto;
import com.ass.brainbitesprototype.security.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingPageController {

    @GetMapping("/")
    public String index(Model model) {
        RegistrationDto user = new RegistrationDto();

        // Redirect somewhere else if already logged in.
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            return "redirect:/sets";
        }

        model.addAttribute("user", user);
        return "index";
    }
}
