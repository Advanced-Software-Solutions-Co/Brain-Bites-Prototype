package com.ass.brainbitesprototype.controllers;

import com.ass.brainbitesprototype.dtos.ClassroomDto;
import com.ass.brainbitesprototype.models.Classroom;
import com.ass.brainbitesprototype.services.impl.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClassroomPageController {
    public ClassroomPageController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }
    @Autowired
    private ClassroomService classroomService;

    @GetMapping("/classrooms")
    public String listClassrooms() {
        return "classrooms-list";
    }

    // TODO Implement create classroom for teacher us only
    @GetMapping("/classrooms/new")
    public String addClassroom(Model model) {
        Classroom classroom = new Classroom();
        model.addAttribute("classroom", classroom);
        return "classrooms-create";
    }

    @GetMapping("/classrooms/dashboard")
    public String dashboardClassroom() {
        return "classrooms-dashboard";
    }
}
