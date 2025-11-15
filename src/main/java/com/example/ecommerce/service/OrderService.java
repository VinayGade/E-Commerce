package com.example.ecommerce.service;

import com.example.ecommerce.entities.Order;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order with id " + id + " not found"));
    }

    public void createOrder(Order Order) {
        orderRepository.save(Order);
    }

    public void updateOrder(Order Order) {
        orderRepository.findById(Order.getId()).orElseThrow(() -> new RuntimeException("Order with id " + Order.getId() + " not found"));
        orderRepository.save(Order);
    }

    public void deleteOrder(Long id) {
        orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order with id " + id + " not found"));
        orderRepository.deleteById(id);
    }

    public List<Order> findOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }
}
