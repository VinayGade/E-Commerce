package com.example.ecommerce.controller;

import com.example.ecommerce.entities.User;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add/user")
    public String addUser(User user){
        userService.createUser(user);
        return "LoginPage";
    }

    @GetMapping("/update/user/{id}")
    public String updateUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "UpdateUser";
    }

    @PostMapping("/update/user")
    public String updateUser(User user) {
        userService.createUser(user);
        return "/admin/home";
    }

    @DeleteMapping("/delete/user/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "/admin/home";
    }
}
