package com.anthonyo.station.rasta.controllers;

import com.anthonyo.station.rasta.entities.Station;
import com.anthonyo.station.rasta.services.StationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class StationControllerTest {

    private StationController stationController;
    private StationService stationService;

    @BeforeEach
    void setUp() {
        stationService = mock(StationService.class);

        stationController = new StationController(stationService);
    }


    @Test
    void createStation() {
        var station = new Station();
        when(stationService.createStation(any())).thenReturn(station);
        var result = stationController.createStation(new Station());
        assertEquals(station, result);
        assertNotNull(result);
        verify(stationService, times(1)).createStation(any());
    }

    @Test
    void getAllStation() {
    }

    @Test
    void getStationById() {
    }

    @Test
    void updateStation() {
    }
}