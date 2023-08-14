package com.spring.service;

import java.util.List;

import com.spring.entity.Product;
import com.spring.exception.ProductNotFoundException;

public interface ProductService {

	List<Product> getProducts();

	void saveProduct(Product product);

	Product getProductById(Integer id) throws ProductNotFoundException;

}
