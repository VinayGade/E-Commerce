package com.example.ecommerce.service;

import com.example.ecommerce.entities.Admin;
import com.example.ecommerce.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAll(){
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id){
        return adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin with id " + id + " not found"));
    }

    public void createUser(Admin admin){
        adminRepository.save(admin);
    }

    public void updateAdmin(Admin admin) {
        adminRepository.findById(admin.getId()).orElseThrow(() -> new RuntimeException("Admin with id " + admin.getId() + " not found"));
        adminRepository.save(admin);
    }

    public void deleteAdmin(Long id) {
        adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin with id " + id + " not found"));
        adminRepository.deleteById(id);
    }

    public boolean verifyCredentials(String email, String password){

        Admin admin = adminRepository.findByEmail(email);
        if(admin.getPassword() == password){
            return true;
        }
        return false;
    }
}
