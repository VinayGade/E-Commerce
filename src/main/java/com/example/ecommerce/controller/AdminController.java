package com.example.ecommerce.controller;

import com.example.ecommerce.entities.Admin;
import com.example.ecommerce.entities.Order;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.service.AdminService;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    //@GetMapping("/admin/verify/credentials")
    @GetMapping("/admin/verify/credentials")
    public String verifyCredentials(@ModelAttribute Admin admin, Model model){
        if(adminService.verifyCredentials(admin.getEmail(), admin.getPassword())){
            model.addAttribute("admin", new Admin());
            model.addAttribute("user", new User());
            model.addAttribute("product", new Product());
            return "redirect:/admin/home";
        }
        model.addAttribute("error", "Invalid email or password...");
        return "LoginPage";
    }

    @GetMapping("/admin/home")
    public String adminHomePage(Model model){

        List<Admin> admins = adminService.getAll();
        List<User> users = userService.getAll();
        List<Product> products = productService.getAll();
        List<Order> orders = orderService.getAll();

        model.addAttribute("adminList", admins);
        model.addAttribute("userList", users);
        model.addAttribute("productList", products);
        model.addAttribute("orderList", orders);

        return "AdminHomePage";
    }

    @PostMapping("/add/admin")
    public String createAdmin(Admin admin){
        adminService.createUser(admin);
        return "redirect:/admin/home";
    }

    @GetMapping("/update/admin/{id}")
    public String update(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("admin", adminService.getAdminById(id));
        return "UpdateAdmin";
    }

    @PostMapping("/update/admin")
    public String updateAdmin(Admin admin) {
        adminService.updateAdmin(admin);
        return "redirect:/admin/home";
    }


    @DeleteMapping("/delete/admin/{id}")
    public String deleteAdmin(@PathVariable Long id){
        adminService.deleteAdmin(id);
        return "redirect:/admin/home";
    }

    @PostMapping("/user/login")
    public String userLogin(User user, Model model){

        String email = user.getEmail();

        if(userService.verifyCredentials(email, user.getPassword())){
            user = userService.findUserByEmail(email);
            List<Order> orders = orderService.findOrdersByUser(user);
            model.addAttribute("orderList", orders);
            return "Products";
        }

        model.addAttribute("error", "Invalid email or passowrd...");
        return "LoginPage";
    }

    @GetMapping("/place/order")
    public String placeOrder(Order order, Model model){
        double totalAmount = order.getPrice() * order.getQuantity();
        order.setAmount(totalAmount);
        /*
        User user = userService.getUserById(userId);
        order.setUser(user);
         */
        order.setDate(new Date());
        orderService.createOrder(order);
        model.addAttribute("amount", totalAmount);
        return "OrderStatus";
    }

    @PostMapping("/product/search")
    public String productSearch(String name, Long userId, Model model) {
        Product product = productService.findProductByName(name);
        User user = userService.getUserById(userId);

        if (product != null) {
            model.addAttribute("ordersList", orderService.findOrdersByUser(user));
            model.addAttribute("product", product);
            return "ProductPage";
        }
        model.addAttribute("Error", "Sorry, product was not found...");
        model.addAttribute("orderList", orderService.findOrdersByUser(user));
        return "ProductPage";
    }
}
