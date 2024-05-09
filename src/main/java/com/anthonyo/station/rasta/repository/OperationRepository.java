package com.anthonyo.station.rasta.repository;

import com.anthonyo.station.rasta.dtos.Facture;
import com.anthonyo.station.rasta.entities.Operation;
import com.anthonyo.station.rasta.entities.Station;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperationRepository {
    Operation createTransaction(Operation toCreate);
    Optional<Station> findByIdStation(Integer id);
    Optional<Station> findByIdProduct(Integer id);
    List<Facture> getAllOperations();
}
