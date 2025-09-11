package com.ecommerce.product.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findByProductCode(String productCode);
}
