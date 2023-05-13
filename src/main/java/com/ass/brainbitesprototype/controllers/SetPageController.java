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
import org.thymeleaf.model.IAttribute;

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
    public String listSets(Model model) {
        List<SetDto> sets = setService.findAllSets();
        model.addAttribute("sets", sets);
        return "sets-list";
    }

    @GetMapping("/sets/{setId}")
    public String setDetail(@PathVariable("setId") long setId, Model model) {
        SetDto setDto = setService.findSetById(setId);
        model.addAttribute("set", setDto);
        return "sets-detail";
    }

    @GetMapping("/sets/new")
    public String createSetForm(Model model) {
        Set set = new Set();
        model.addAttribute( "set", set);
        return "sets-create";
    }

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
    public String editSetForm(@PathVariable("setId") long setId, Model model) {
        SetDto set = setService.findSetById(setId);
        model.addAttribute("set", set);
        return "sets-edit";
    }

    @PostMapping("/sets/{setId}/edit")
    public String updateSet(@PathVariable("setId") Long setId, @Valid @ModelAttribute("set") SetDto set, BindingResult result) {
        if(result.hasErrors()) {
            return "sets-edit";
        }
        set.setId(setId);
        setService.updateSet(set);
        return "redirect:/sets";
    }




}
