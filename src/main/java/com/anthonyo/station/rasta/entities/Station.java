package com.anthonyo.station.rasta.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Station {
    private Integer id;
    private String name;
    private String place;
    private Double maxVolumeGasoline;
    private Double maxVolumeDiesel;
    private Double maxVolumePetrol;
}
