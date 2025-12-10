package com.phasezero.catalog_service.repository;

import com.phasezero.catalog_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    
    boolean existsByPartNumber(String partNumber);

    
    List<Product> findByPartNameContainingIgnoreCase(String partName);

    
    List<Product> findByCategoryIgnoreCase(String category);

    
    List<Product> findAllByOrderByPriceAsc();
}
