package com.skilllink.controller;

import com.skilllink.model.User;
import com.skilllink.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String save(@Valid User user, BindingResult result, RedirectAttributes redirect){

        if(result.hasErrors()){
            return "register";
        }

        service.saveUser(user);
        redirect.addFlashAttribute("success", "Usuario creado correctamente");

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model){
        User user = service.getUserById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/update")
    public String updateUser(@Valid User user, BindingResult result, RedirectAttributes redirect){

        if(result.hasErrors()){
            return "edit";
        }

        service.saveUser(user);
        redirect.addFlashAttribute("success", "Usuario actualizado");

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirect){
        service.deleteUser(id);
        redirect.addFlashAttribute("success", "Usuario eliminado");
        return "redirect:/";
    }
}