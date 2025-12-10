package com.phasezero.catalog_service.repository;

import com.phasezero.catalog_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Check duplicate partNumber
    boolean existsByPartNumber(String partNumber);

    // Search by partName (case-insensitive)
    List<Product> findByPartNameContainingIgnoreCase(String partName);

    // Filter by category (case-insensitive)
    List<Product> findByCategoryIgnoreCase(String category);

    // Sort by price ascending
    List<Product> findAllByOrderByPriceAsc();
}
