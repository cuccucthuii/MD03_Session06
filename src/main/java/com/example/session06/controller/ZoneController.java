package com.example.session06.controller;

import com.example.session06.model.dto.zone.ZoneStatisticsResponse;
import com.example.session06.service.IZoneService;
import com.example.session06.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ZoneController {
    @Autowired
    private IZoneService zoneService;

    @GetMapping("/api/v1/zones/stats")
    public ResponseEntity<ApiResponse<List<ZoneStatisticsResponse>>> getAvailableZones(){
        List<ZoneStatisticsResponse> zoneList = zoneService.getAvailableZones();
        ApiResponse<List<ZoneStatisticsResponse>> response = new ApiResponse<>(true,"Success",zoneList);
        return new ResponseEntity<>(response, HttpStatus.OK);//200
    }

    @GetMapping("/api/v2/zones/stats")
    public ResponseEntity<ApiResponse<List<ZoneStatisticsResponse>>> getAvailableZonesV2(){
        List<ZoneStatisticsResponse> zoneList = zoneService.getAvailableZonesV2();
        ApiResponse<List<ZoneStatisticsResponse>> response = new ApiResponse<>(true,"Success",zoneList);
        return new ResponseEntity<>(response, HttpStatus.OK);//200
    }
}
