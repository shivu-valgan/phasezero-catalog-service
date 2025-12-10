package com.phasezero.catalog_service.service;

import com.phasezero.catalog_service.entity.Product;
import com.phasezero.catalog_service.exception.DataExistsException;
import com.phasezero.catalog_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public Product addProduct(Product product) {

        product.setPartName(product.getPartName().toLowerCase());

        if (productRepository.existsByPartNumber(product.getPartNumber())) {
            throw new DataExistsException("Product with this partNumber already exists");
        }

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> searchByName(String name) {
        return productRepository.findByPartNameContainingIgnoreCase(name);
    }

    public List<Product> filterByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    public List<Product> sortByPrice() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    public double getTotalInventoryValue() {
        return productRepository.findAll()
                .stream()
                .mapToDouble(p -> p.getPrice() * p.getStock())
                .sum();
    }
}
