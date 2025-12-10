package com.phasezero.catalog_service.service;

import java.util.List;

import com.phasezero.catalog_service.entity.Product;

import jakarta.validation.Valid;

public interface ProductService {

	double getTotalInventoryValue();

	List<Product> sortByPrice();

	List<Product> filterByCategory(String category);

	List<Product> searchByName(String name);

	List<Product> getAllProducts();

	Product addProduct(@Valid Product product);

}
