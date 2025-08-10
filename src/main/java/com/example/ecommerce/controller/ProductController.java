package com.example.ecommerce.controller;

import com.example.ecommerce.entities.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public String addProduct(Product product){
        productService.createProduct(product);
        return "/admin/home";
    }

    @GetMapping("/add")
    public String addProduct(){
        return "AddProduct";
    }

    @PostMapping("/update")
    public String updateProduct(Product product){
        productService.createProduct(product);
        return "/admin/home";
    }


    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "updateProduct";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "/admin/home";
    }
}
