package com.example.ecommerce.repository;

import com.example.ecommerce.entities.Admin;
import com.example.ecommerce.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    @Transactional
    @Modifying
    @Query(
            value = "ALTER TABLE products " +
                    "AUTO_INCREMENT = (SELECT IFNULL(MAX(id), 0) + 1 FROM products)",
            nativeQuery = true
    )
    void resetProductsSequence();
}
