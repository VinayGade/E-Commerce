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


@Controller("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    private User user;

    @GetMapping("/verify-credentials")
    public String verifyCredentials(@ModelAttribute Admin admin, Model model){
        if(adminService.verifyCredentials(admin.getEmail(), admin.getPassword())){
            return "/admin/home";
        }
        model.addAttribute("error", "Invalid email or password...");
        return "Login";
    }

    @GetMapping("/home")
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

    @GetMapping("/add")
    public String createAdmin(){
        return "CreateAdmin";
    }

    @PostMapping("/add")
    public String createAdmin(Admin admin){
        adminService.createUser(admin);
        return "/admin/home";
    }

    @GetMapping("/update/{id}")
    public String updateAdmin(@PathVariable Long id, Model model){
        model.addAttribute("admin", adminService.getAdminById(id));
        return "updateAdmin";
    }

    @PostMapping("/update")
    public String updateAdmin(Admin admin){
        adminService.updateAdmin(admin);
        return "/admin/home";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable Long id){
        adminService.deleteAdmin(id);
        return "/admin/home";
    }

    @PostMapping("/user/login")
    public String userLogin(User user, Model model){

        String email = user.getEmail();

        if(userService.verifyCredentials(email, user.getPassword())){
            this.user = userService.findUserByEmail(email);
            List<Order> orders = orderService.findOrdersByUser(user);
            return "product-page";
        }

        model.addAttribute("error", "Invalid email or passowrd...");
        return "Login";
    }

    @GetMapping("/place/order")
    public String placeOrder(Order order, Model model){
        double totalAmount = order.getPrice() * order.getQuantity();
        order.setAmount(totalAmount);
        order.setUser(user);
        order.setDate(new Date());
        orderService.createOrder(order);
        model.addAttribute("amount", totalAmount);
        return "OrderStatus";
    }

    @PostMapping("/product/search")
    public String productSearch(String name, Long userId, Model model) {
        Product product = productService.findProductByName(name);
        //User user = userService.getUserById(userId);

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
