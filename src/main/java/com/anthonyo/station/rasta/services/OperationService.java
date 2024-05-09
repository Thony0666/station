package com.anthonyo.station.rasta.services;

import com.anthonyo.station.rasta.dtos.BuyByMoneyProduct;
import com.anthonyo.station.rasta.dtos.Facture;
import com.anthonyo.station.rasta.dtos.SupplyStation;
import com.anthonyo.station.rasta.entities.Operation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OperationService {
    Operation createOperation(BuyByMoneyProduct toCreateOperation);
    Operation createOperation(SupplyStation toCreateOperation);
    List<Facture> getAllOperations();
}
