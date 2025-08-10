package com.example.ecommerce.service;

import com.example.ecommerce.entities.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
    }

    public void createProduct(Product Product) {
        productRepo.save(Product);
    }

    public void updateProduct(Product Product) {
        productRepo.findById(Product.getId()).orElseThrow(() -> new RuntimeException("Product with id " + Product.getId() + " not found"));
        productRepo.save(Product);
    }

    public void deleteProduct(Long id) {
        productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
        productRepo.deleteById(id);
    }

    public Product findProductByName(String name) {
        return productRepo.findByName(name);
    }
}
