package com.anthonyo.station.rasta.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Product {
    private Integer id;
    private String name;
    private Double price;
    private Double quantity;
}
