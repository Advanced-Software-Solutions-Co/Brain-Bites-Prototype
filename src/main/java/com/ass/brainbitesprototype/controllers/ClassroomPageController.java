package com.ass.brainbitesprototype.controllers;

import com.ass.brainbitesprototype.dtos.ClassroomDto;
import com.ass.brainbitesprototype.models.Classroom;
import com.ass.brainbitesprototype.models.UserEntity;
import com.ass.brainbitesprototype.security.SecurityUtil;
import com.ass.brainbitesprototype.services.ClassroomService;
import com.ass.brainbitesprototype.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClassroomPageController {
    @Autowired
    public ClassroomPageController(ClassroomService classroomService, UserService userService) {
        this.classroomService = classroomService;
        this.userService = userService;
    }
    private ClassroomService classroomService;
    private UserService userService;

    @GetMapping("/classrooms")
    public String listClassrooms(Model model) {
        // Insert profile picture of user into navbar if authenticated.
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        return "classrooms-list";
    }

    // TODO Implement create classroom for teacher us only
    @GetMapping("/classrooms/new")
    public String addClassroom(Model model) {
        Classroom classroom = new Classroom();
        model.addAttribute("classroom", classroom);

        // Insert profile picture of user into navbar if authenticated.
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);

        return "classrooms-create";
    }

    @GetMapping("/classrooms/dashboard")
    public String dashboardClassroom(Model model) {

        // Insert profile picture of user into navbar if authenticated.
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);

        return "classrooms-dashboard";
    }
    @PostMapping("/classrooms/new")
    public String saveClassroom(@Valid @ModelAttribute("classroom") ClassroomDto classroomDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("classroom", classroomDto);
            return "classrooms-create";
        }
        classroomService.saveClassroom(classroomDto);
        return "redirect:/classrooms";
    }
}
