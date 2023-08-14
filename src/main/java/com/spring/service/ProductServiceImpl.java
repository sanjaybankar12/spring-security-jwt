package com.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.Product;
import com.spring.exception.ProductNotFoundException;
import com.spring.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getProducts() {
		return this.productRepository.findAll();
	}
	
	@Override
	public void saveProduct(Product product) {
		if(product != null) {
			this.productRepository.save(product);
		}
	}

	@Override
	public Product getProductById(Integer id) throws ProductNotFoundException {
		Optional<Product> optProduct = this.productRepository.findById(id);
		if(optProduct.isEmpty()) {
			throw new ProductNotFoundException(404, "Product is not found");
		}
		return optProduct.get();
	}
}
