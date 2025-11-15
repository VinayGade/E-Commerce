package com.example.ecommerce.repository;

import com.example.ecommerce.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Message, Long> {

}
