package com.example.session06.service;

import com.example.session06.model.dto.zone.ZoneStatisticsResponse;

import java.util.List;

public interface IZoneService {
    List<ZoneStatisticsResponse> getAvailableZones();
    List<ZoneStatisticsResponse> getAvailableZonesV2();
}
