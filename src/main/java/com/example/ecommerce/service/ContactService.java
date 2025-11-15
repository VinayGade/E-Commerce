package com.example.ecommerce.service;

import com.example.ecommerce.entities.Message;
import com.example.ecommerce.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Message> getAllMessage() {
        return contactRepository.findAll();
    }

    public Message getMessageById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Message with id " + id + " not found"));
    }

    public void createMessage(Message Message) {
        contactRepository.save(Message);
    }

    public void updateMessage(Message Message) {
        contactRepository.findById(Message.getId()).orElseThrow(() -> new RuntimeException("Message with id " + Message.getId() + " not found"));
        contactRepository.save(Message);
    }

    public void deleteMessage(Long id) {
        contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Message with id " + id + " not found"));
        contactRepository.deleteById(id);
    }
}
