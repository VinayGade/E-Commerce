package com.example.ecommerce.repository;

import com.example.ecommerce.entities.Admin;
import com.example.ecommerce.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Transactional
    @Modifying
    @Query(
            value = "ALTER TABLE users " +
                    "AUTO_INCREMENT = (SELECT IFNULL(MAX(id), 0) + 1 FROM users)",
            nativeQuery = true
    )
    void resetUsersSequence();
}
