package com.ass.brainbitesprototype.controllers;

import com.ass.brainbitesprototype.dtos.SetDto;
import com.ass.brainbitesprototype.models.Set;
//import service
import com.ass.brainbitesprototype.models.UserEntity;
import com.ass.brainbitesprototype.security.SecurityUtil;
import com.ass.brainbitesprototype.services.SetService;
import com.ass.brainbitesprototype.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SetPageController {
    private SetService setService;
    private UserServiceImpl userService;

    @Autowired
    public SetPageController(SetService setService, UserServiceImpl userService) {
        this.setService = setService;
        this.userService = userService;
    }

    @GetMapping("/sets")
    //"set" is what you usually want to use when dealing with thymeleaf and database
    public String listSets(Model model) {
        List<SetDto> sets = setService.findAllSets();
        model.addAttribute("sets", sets);

        // Insert profile picture of user into navbar if authenticated.
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);

        return "sets-list";
    }

    @GetMapping("/sets/{setId}")
    public String setDetail(@PathVariable("setId") Long setId, Model model) {
        SetDto setDto = setService.findSetById(setId);
        model.addAttribute("set", setDto);

        // Insert profile picture of user into navbar if authenticated.
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);

        return "sets-detail";
    }

    @GetMapping("/sets/new")
    public String createSetForm(Model model) {
        Set set = new Set();
        model.addAttribute( "set", set);

        // Insert profile picture of user into navbar if authenticated.
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);

        return "sets-create";
    }

    @GetMapping("/sets/{setId}/delete")
    public String deleteSet(@PathVariable("setId") Long setId){
        setService.delete(setId);
        return "redirect:/sets";
    }

    /*
    @GetMapping("/sets/search")
    public String searchSet(@RequestParam(value = "query") String query, Model model){
        List<SetDto> sets = setService.searchSets(query);
        model.addAttribute("sets", sets);

        // Insert profile picture of user into navbar if authenticated.
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);

        return "sets-list";
    }
    */


    @PostMapping("/sets/new")
    public String saveSet(@Valid @ModelAttribute("set") SetDto setDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("set", setDto);
            return "sets-create";
        }
        setService.saveSet(setDto);
        return "redirect:/sets";
    }

    @GetMapping("/sets/{setId}/edit")
    public String editSetForm(@PathVariable("setId") Long setId, Model model) {
        SetDto set = setService.findSetById(setId);
        model.addAttribute("set", set);

        // Insert profile picture of user into navbar if authenticated.
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);

        return "sets-edit";
    }

    @PostMapping("/sets/{setId}/edit")
    public String updateSet(@PathVariable("setId") Long setId, @Valid @ModelAttribute("set") SetDto set, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("set", set);
            return "sets-edit";
        }
        set.setId(setId);
        setService.updateSet(set);
        return "redirect:/sets";
    }

}
