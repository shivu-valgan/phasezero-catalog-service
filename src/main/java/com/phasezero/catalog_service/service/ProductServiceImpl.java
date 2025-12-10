package com.phasezero.catalog_service.service;

import com.phasezero.catalog_service.entity.Product;
import com.phasezero.catalog_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    // 1) Add Product
    public Product addProduct(Product product) {

        // Normalize partName to lowercase (business rule)
        product.setPartName(product.getPartName().toLowerCase());

        // No duplicate partNumber allowed
        if (productRepository.existsByPartNumber(product.getPartNumber())) {
            throw new RuntimeException("Product with this partNumber already exists");
        }

        return productRepository.save(product);
    }

    // 2) List All Products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 3) Search by Name
    public List<Product> searchByName(String name) {
        return productRepository.findByPartNameContainingIgnoreCase(name);
    }

    // 4) Filter by Category
    public List<Product> filterByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    // 5) Sort by Price
    public List<Product> sortByPrice() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    // 6) Inventory Value = Σ(price * stock)
    public double getTotalInventoryValue() {
        return productRepository.findAll()
                .stream()
                .mapToDouble(p -> p.getPrice() * p.getStock())
                .sum();
    }
}
