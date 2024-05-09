package com.anthonyo.station.rasta.services.impl;

import com.anthonyo.station.rasta.entities.Product;
import com.anthonyo.station.rasta.exceptions.InternalServerException;
import com.anthonyo.station.rasta.repository.ProductRepository;
import com.anthonyo.station.rasta.services.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private  final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product createProduct(Product toCreate) {
            if (toCreate.getId() == null){
                toCreate.setQuantity(0.00);
                return productRepository.createProduct(toCreate);
            }
            if (toCreate.getName() != ""){
                return productRepository.createProduct(toCreate);
            }
            else {
                throw new InternalServerException("Error of creation station");
            }

    }
}
