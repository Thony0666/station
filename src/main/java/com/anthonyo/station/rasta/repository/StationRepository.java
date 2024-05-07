package com.anthonyo.station.rasta.repository;

import com.anthonyo.station.rasta.entities.Station;

import java.util.List;
import java.util.Optional;

public interface StationRepository {
    Station createStation(Station toCreate);
    List<Station> findAllStation();
    Optional <Station>findById(Integer id);
}

