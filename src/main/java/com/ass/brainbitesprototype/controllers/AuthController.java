package com.ass.brainbitesprototype.controllers;

import com.ass.brainbitesprototype.dtos.RegistrationDto;
import com.ass.brainbitesprototype.models.UserEntity;
import com.ass.brainbitesprototype.security.SecurityUtil;
import com.ass.brainbitesprototype.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UserServiceImpl userService;

    @Autowired
    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        // Redirect somewhere else if already logged in.
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            return "redirect:/sets";
        }
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();

        // Redirect somewhere else if already logged in.
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            return "redirect:/sets";
        }

        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user,
                           BindingResult result,
                           Model model) {
        // An email is already being used.
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if (existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            return "redirect:/register?auth_fail";
        }

        // Passwords don't match.
        String password = user.getPassword();
        String passwordConfirm = user.getPasswordConfirm();
        if (!password.equals(passwordConfirm)) {
            return "redirect:/register?match_fail";
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }

        userService.saveUser(user);

        System.out.println(user.getUsername() + " has been added!");
        return "redirect:/sets?success";  // TODO change to home?success maybe
    }
}
