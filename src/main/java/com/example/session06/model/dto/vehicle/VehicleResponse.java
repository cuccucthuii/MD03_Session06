package com.example.session06.model.dto.vehicle;

import com.example.session06.util.TypeVehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponse {
//    VehicleResponse ( Long id, String licensePlate, String color, VehicleType vehicleType )
    private Long vehicleId;
    private String licensePlate;
    private String color;
    private TypeVehicle type;
}
