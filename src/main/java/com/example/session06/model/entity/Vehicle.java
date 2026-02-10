package com.example.session06.model.entity;

import com.example.session06.util.TypeVehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vehicles")
public class Vehicle {
//    Trường: id, licensePlate (biển số xe), color (màu sắc), type (loại xe: CAR, BIKE - sử dụng Enum).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    private String licensePlate;
    private String color;
    private TypeVehicle type;

    @OneToMany(mappedBy = "vehicle")
    private List<ParkingTicket> parkingTickets;
}
