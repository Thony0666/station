package com.anthonyo.station.rasta.services;

import com.anthonyo.station.rasta.entities.Station;

import java.util.List;

public interface StationService {
    Station createStation(Station toCreate);
    Station updateStation(Station toUpdate);
    List<Station> findAllStation();
    Station findById(Integer id);
}
