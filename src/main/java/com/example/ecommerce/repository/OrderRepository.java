package com.example.ecommerce.repository;

import com.example.ecommerce.entities.Order;

import com.example.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

    Order findByName(String name);

    List<Order> findByUser(User user);
}
