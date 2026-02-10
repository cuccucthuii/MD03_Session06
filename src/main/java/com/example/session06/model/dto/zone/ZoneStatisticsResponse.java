package com.example.session06.model.dto.zone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ZoneStatisticsResponse {
    //id, name, capacity, occupiedSlots, availableSlots.
    private Long zoneId;
    private String zoneName;
    private int capacity;
    private int occupiedSpots;
    private int availableSpots;
}
