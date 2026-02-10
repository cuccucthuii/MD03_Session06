package com.example.session06.model.entity;

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
@Table(name = "zones")
public class Zone {
    //Trường: id, name (tên khu vực), capacity (sức chứa tối đa), occupiedSpots (số chỗ đã dùng)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long zoneId;
    private String zoneName;
    private int capacity;
    private int occupiedSpots;

    @OneToMany(mappedBy = "zone") //ten value
    private List<ParkingTicket> parkingTickets;
}
