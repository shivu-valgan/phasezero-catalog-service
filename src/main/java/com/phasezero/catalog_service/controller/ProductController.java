package com.phasezero.catalog_service.controller;


import com.phasezero.catalog_service.entity.Product;
import com.phasezero.catalog_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 1) Add New Product
    @PostMapping("/")
    public Product addProduct(@Valid @RequestBody Product product) {
        return productService.addProduct(product);
    }

    // 2) List All Products
    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // 3) Search by Name
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String name) {
        return productService.searchByName(name);
    }

    // 4) Filter by Category
    @GetMapping("/filter")
    public List<Product> filterByCategory(@RequestParam String category) {
        return productService.filterByCategory(category);
    }

    // 5) Sort by Price
    @GetMapping("/sort/price")
    public List<Product> sortByPrice() {
        return productService.sortByPrice();
    }

    // 6) Total Inventory Value
    @GetMapping("/inventory/value")
    public double inventoryValue() {
        return productService.getTotalInventoryValue();
    }
}

