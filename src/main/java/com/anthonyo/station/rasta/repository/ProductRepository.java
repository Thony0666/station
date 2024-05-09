package com.anthonyo.station.rasta.repository;

import com.anthonyo.station.rasta.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository {
    Product createProduct(Product toCreate);
    Product updateProductById(Product toUpdateById);
    Optional<Product> findById(Integer id);
}
