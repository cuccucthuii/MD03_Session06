package com.example.session06.service;

import com.example.session06.model.dto.vehicle.VehicleRequest;
import com.example.session06.model.dto.vehicle.VehicleResponse;
import com.example.session06.util.PageResponse;

public interface IVehicleService {
    // GET PAGE VEHICLE (Y/C Luyen Tap 01 )
    PageResponse<VehicleResponse> getPagedVehicles(int page, int size, String sortBy, String direction, String keyword);
    // Create Vehicle ( Y/C practice 01 )
    VehicleResponse createVehicle(VehicleRequest vehicleRequest);
}
