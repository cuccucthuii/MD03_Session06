package com.example.session06.service.impl;

import com.example.session06.model.dto.zone.ZoneStatisticsResponse;
import com.example.session06.model.entity.Zone;
import com.example.session06.repository.IZoneRepository;
import com.example.session06.service.IZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneServiceImpl implements IZoneService {
    @Autowired
    private IZoneRepository zoneRepository;


    @Override
    public List<ZoneStatisticsResponse> getAvailableZones() {
        List<Zone> list = zoneRepository.findAll();
        return list.stream()
                .map(zone -> {
                    int availableSlot = zone.getCapacity() - zone.getOccupiedSpots();
                    return new ZoneStatisticsResponse(
                            zone.getZoneId(),
                            zone.getZoneName(),
                            zone.getCapacity(),
                            zone.getOccupiedSpots(),
                            availableSlot
                    );
                }).toList();
    }

    @Override
    public List<ZoneStatisticsResponse> getAvailableZonesV2() {
        List<ZoneStatisticsResponse> list = zoneRepository.getAvaliableZones();
        return list;
    }
}
