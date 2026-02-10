package com.example.session06.controller;

import com.example.session06.model.dto.vehicle.VehicleRequest;
import com.example.session06.model.dto.vehicle.VehicleResponse;
import com.example.session06.service.IVehicleService;
import com.example.session06.util.ApiResponse;
import com.example.session06.util.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {
    @Autowired
    private IVehicleService vehicleService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<VehicleResponse>>> getPagedVehicle(
            @RequestParam(defaultValue = "0") int page, // vi trí trang
            @RequestParam(defaultValue = "5") int size, // Số trang mặc định
            @RequestParam(required = false) String sortBy, // Sắp xếp theo tiêu chí nào -- Không yêu cầu
            @RequestParam(required = false) String direction, // Sắp xếp theo thứ tự nào ( asc/desc) -- Không yêu cầu
            @RequestParam(required = false) String keyword){ // Lọc theo biển số xe - Không yêu cầu
        PageResponse<VehicleResponse> pageResponse = vehicleService.getPagedVehicles(page, size, sortBy, direction, keyword);
        ApiResponse<PageResponse<VehicleResponse>> apiResponse = new ApiResponse<>(true, "Successfully",pageResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK); //200
    }

    // Create Vehicle
    @PostMapping
    public ResponseEntity<ApiResponse<VehicleResponse>> createVehicle(@RequestBody VehicleRequest request){
        VehicleResponse vehicleResponse = vehicleService.createVehicle(request);
        ApiResponse<VehicleResponse> apiResponse = new ApiResponse<>(true, "Successfully",vehicleResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED); // 201
    }
}
