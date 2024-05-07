package com.anthonyo.station.rasta.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@Builder
public class Operation {
    private Integer id ;
    private Integer idStation      ;
    private Integer idProduct;
    private  String type ;
    private  Double quantity     ;
    private Double amounts ;
    private LocalDateTime dateOperation ;
}
