package com.anthonyo.station.rasta.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Station {
    private Integer id;
    private String name;
    private String place;
    private Double maxVolumeGasoline;
    private Double maxVolumeDiesel;
    private Double maxVolumePetrol;
}
