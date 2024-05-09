package com.anthonyo.station.rasta.controllers;

import com.anthonyo.station.rasta.entities.Station;
import com.anthonyo.station.rasta.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/stations")
public class StationController {
    private final StationService stationService;

    @Autowired
    public StationController(@Qualifier("stationService2") StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping("/create")
    public Station createStation (@RequestBody Station station){
        return stationService.createStation(station);
    }
    @GetMapping
    public List<Station> getAllStation(){
        return stationService.findAllStation();
    }
    @GetMapping("/{id}")
    public Station getStationById(@PathVariable Integer id){
        return stationService.findById(id);
    }
    @PutMapping("/put")
    public Station updateStation(@RequestBody Station station){
        return stationService.updateStation(station);
    }
}
