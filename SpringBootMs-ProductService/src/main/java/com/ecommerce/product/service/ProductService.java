package com.ecommerce.product.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.product.model.Product;
import com.ecommerce.product.respository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public Product saveProduct(Product product) {
        return repo.save(product);
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Optional<Product> getByCode(String code) {
        return repo.findByProductCode(code);
    }
}
