package com.example.session06.mapper;

import com.example.session06.model.dto.vehicle.VehicleRequest;
import com.example.session06.model.dto.vehicle.VehicleResponse;
import com.example.session06.model.entity.Vehicle;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring") // Khai báo bean MapStruct Mapper
public interface VehicleMapper {
    // Nếu tên khác nhau thì phải khai báo @Mapping để convert giữa ENTYTY và DTO
    // Get ( entity -> dto )
    VehicleResponse toResponse(Vehicle vehicle);
    // Get List
    List<VehicleResponse> toResponseList(List<Vehicle> vehicles);
    // Post ( dto -> entity )
    Vehicle toEntity(VehicleRequest vehicleRequest);
}
