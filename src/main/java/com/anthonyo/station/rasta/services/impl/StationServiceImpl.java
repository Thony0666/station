package com.anthonyo.station.rasta.services.impl;

import com.anthonyo.station.rasta.entities.Station;
import com.anthonyo.station.rasta.exceptions.InternalServerException;
import com.anthonyo.station.rasta.exceptions.NotFoundException;
import com.anthonyo.station.rasta.repository.StationRepository;
import com.anthonyo.station.rasta.services.StationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {
    private final StationRepository stationRepository ;
    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }


    @Override
    public Station createStation(Station toCreate) {

        if (toCreate.getId()==null){
            return stationRepository.createStation(toCreate);
        }
        else {
        throw new InternalServerException("Error of creation station");
        }
    }

    @Override
    public List<Station> findAllStation() {
        return stationRepository.findAllStation();
    }

    @Override
    public Station findById(Integer id) {
        return stationRepository.findById(id).orElseThrow(()->new NotFoundException("Station Not Found"));
    }
}
