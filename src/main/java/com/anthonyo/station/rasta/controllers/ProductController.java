package com.anthonyo.station.rasta.controllers;

import com.anthonyo.station.rasta.entities.Product;
import com.anthonyo.station.rasta.services.ProductService;
import com.anthonyo.station.rasta.services.impl.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productServiceImpl;

    public ProductController(ProductService productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        return productServiceImpl.createProduct(product);
    }
}
