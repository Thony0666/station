package com.anthonyo.station.rasta.controllers;

import com.anthonyo.station.rasta.dtos.BuyByMoneyProduct;
import com.anthonyo.station.rasta.dtos.Facture;
import com.anthonyo.station.rasta.dtos.SupplyStation;
import com.anthonyo.station.rasta.entities.Operation;
import com.anthonyo.station.rasta.services.OperationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/operation")
public class OperationController {
    private final OperationService operationService;
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }


    @PostMapping("/buy")
    public Operation create(@RequestBody BuyByMoneyProduct operation) {
        return operationService.createOperation(operation);
    }
    @PostMapping("/supply")
    public Operation create(@RequestBody SupplyStation operation) {
        return operationService.createOperation(operation);
    }
    @GetMapping("/facture")
    public List<Facture> getAllOperations() {
        return operationService.getAllOperations();
    }
}
