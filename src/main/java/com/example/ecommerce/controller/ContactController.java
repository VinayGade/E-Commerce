package com.example.ecommerce.controller;

import com.example.ecommerce.entities.Message;
import com.example.ecommerce.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/send/message")
    public String sendMessage(Message message, Model model){
        contactService.createMessage(message);
        model.addAttribute("Confirmation", "Your message has been successfully sent...");
        return "ContactUs";
    }
}
