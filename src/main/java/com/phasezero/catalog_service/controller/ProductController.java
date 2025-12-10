package com.phasezero.catalog_service.controller;


import com.phasezero.catalog_service.entity.Product;
import com.phasezero.catalog_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@Valid @RequestBody Product product) {
        return productService.addProduct(product);
    }

    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> searchProducts(@RequestParam String name) {
        return productService.searchByName(name);
    }

    
    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> filterByCategory(@RequestParam String category) {
        return productService.filterByCategory(category);
    }

    
    @GetMapping("/sort/price")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> sortByPrice() {
        return productService.sortByPrice();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/inventory/value")
    public double inventoryValue() {
        return productService.getTotalInventoryValue();
    }
}

