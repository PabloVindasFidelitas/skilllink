package com.skilllink.controller;

import com.skilllink.model.User;
import com.skilllink.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("users", service.getAllUsers());
        return "index";
    }

    @GetMapping("/register")
    public String form(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/save")
    public String save(User user){
        service.saveUser(user);
        return "redirect:/";
    }
}