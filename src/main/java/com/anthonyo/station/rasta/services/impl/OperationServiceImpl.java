package com.anthonyo.station.rasta.services.impl;

import com.anthonyo.station.rasta.dtos.BuyByMoneyProduct;
import com.anthonyo.station.rasta.dtos.Facture;
import com.anthonyo.station.rasta.dtos.SupplyStation;
import com.anthonyo.station.rasta.entities.Operation;
import com.anthonyo.station.rasta.enums.TransactionType;
import com.anthonyo.station.rasta.exceptions.NotFoundException;
import com.anthonyo.station.rasta.repository.OperationRepository;
import com.anthonyo.station.rasta.repository.ProductRepository;
import com.anthonyo.station.rasta.repository.StationRepository;
import com.anthonyo.station.rasta.services.OperationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {
    private  final OperationRepository operationRepository;
    private final ProductRepository productRepository;
    private final StationRepository stationRepository;
    public OperationServiceImpl(OperationRepository operationRepository, ProductRepository productRepository, StationRepository stationRepository) {
        this.operationRepository = operationRepository;
        this.productRepository = productRepository;
        this.stationRepository = stationRepository;
    }

    @Override
    public Operation createOperation(BuyByMoneyProduct toCreateOperation ) {
        var productOptional = productRepository.findById(toCreateOperation.idProduct());
        var stationOptional = stationRepository.findById(toCreateOperation.idStation());
        if (productOptional.isEmpty() || stationOptional.isEmpty()) {
            throw new NotFoundException("Product or station does not exist");
        }
        var product = productOptional.get();
        var calculatedQuantity = toCreateOperation.amountValue() / product.getPrice();
        var operation = Operation.builder()
                .dateOperation(LocalDateTime.now())
                .type(TransactionType.SORTIE.name())
                .amounts(toCreateOperation.amountValue())
                .idProduct(toCreateOperation.idProduct())
                .idStation(toCreateOperation.idStation())
                .quantity(calculatedQuantity)
                .build();

        var newQuantity = product.getQuantity() - calculatedQuantity;
        product.setQuantity(newQuantity);
        productRepository.updateProductById(product);

        return operationRepository.createTransaction(operation);
    }
    @Override
    public Operation createOperation(SupplyStation toCreateOperation ) {
        var productOptional = productRepository.findById(toCreateOperation.idProduct());
        var stationOptional = stationRepository.findById(toCreateOperation.idStation());
        if (productOptional.isEmpty() || stationOptional.isEmpty()) {
            throw new NotFoundException("Product or station does not exist");
        }
        var product = productOptional.get();
        var operation = Operation.builder()
                .dateOperation(LocalDateTime.now())
                .type(TransactionType.ENTRY.name())
                .amounts(0.00)
                .idProduct(toCreateOperation.idProduct())
                .idStation(toCreateOperation.idStation())
                .quantity(toCreateOperation.quantity())
                .build();

        var newQuantity = product.getQuantity() + toCreateOperation.quantity();
        product.setQuantity(newQuantity);
        productRepository.updateProductById(product);

        return operationRepository.createTransaction(operation);
    }

    @Override
    public List<Facture> getAllOperations() {


        return operationRepository.getAllOperations();
    }


}
