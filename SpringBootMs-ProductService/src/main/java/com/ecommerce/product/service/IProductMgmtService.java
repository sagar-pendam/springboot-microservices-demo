package com.ecommerce.product.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.product.model.Product;

public interface IProductMgmtService {
	public Product saveProduct(Product product);
	public List<Product> getAllProducts();
	Optional<Product> getByCode(String code);
}
