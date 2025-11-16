package com.example.ecommerce.repository;

import com.example.ecommerce.entities.Admin;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByEmail(String email);

    @Transactional
    @Modifying
    @Query(
            value = "ALTER TABLE admins " +
                    "AUTO_INCREMENT = (SELECT IFNULL(MAX(id), 0) + 1 FROM admins)",
            nativeQuery = true
    )
    void resetAdminSequence();
}
