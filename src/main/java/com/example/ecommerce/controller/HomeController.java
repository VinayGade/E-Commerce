package com.example.ecommerce.controller;

import com.example.ecommerce.entities.Admin;
import com.example.ecommerce.entities.Message;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String productsPage(Model model){
        List<Product> products = productService.getAll();
        model.addAttribute("productList", products);
        return "Products";
    }

    @GetMapping({"/", "/home"})
    public String homePage(){
        return "HomePage";
    }

    @GetMapping("/contactUs")
    public String contactPage(Model model){
        model.addAttribute("message", new Message());
        return "contactUs";
    }

    @GetMapping("/aboutUs")
    public String aboutUs(){
        return "AboutUs";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("admin", new Admin());
        return "LoginPage";
    }
}
