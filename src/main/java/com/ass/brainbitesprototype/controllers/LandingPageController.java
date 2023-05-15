package com.ass.brainbitesprototype.controllers;

import com.ass.brainbitesprototype.dtos.RegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingPageController {

    @GetMapping("/")
    public String index(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "index";
    }
}
