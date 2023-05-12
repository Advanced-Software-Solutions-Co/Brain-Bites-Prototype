package com.ass.brainbitesprototype.controllers;

import com.ass.brainbitesprototype.dtos.SetDto;
import com.ass.brainbitesprototype.models.Set;
//import service
import com.ass.brainbitesprototype.services.SetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.AttributedString;
import java.util.List;

@Controller
public class SetPageController {
    private SetService setService;

    @Autowired
    public SetPageController(SetService setService) {
        this.setService = setService;
    }

    @GetMapping("/sets")
    //"set" is what you usually want to use when dealing with thymeleaf and database
    public String listSets(Model set) {
        List<SetDto> sets = setService.findAllSets();
        set.addAttribute("sets", sets);
        return "sets-list";
    }




}
