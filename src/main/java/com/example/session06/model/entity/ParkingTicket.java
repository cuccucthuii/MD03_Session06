package com.example.session06.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "parking_tickets")
public class ParkingTicket {
    //id: Khóa chính.
    //checkInTime: Thời gian xe vào.
    //checkOutTime: Thời gian xe ra.
    //Vehicle vehicle: Quan hệ @ManyToOne với thực thể Vehicle.
    //Zone zone: Quan hệ @ManyToOne với thực thể Zone (nơi xe thực tế đỗ lúc đó).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingTicketId;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    @ManyToOne
    private Vehicle vehicle;
    @ManyToOne
    private Zone zone;

    @PrePersist
    public void prePersist(){
        checkInTime = LocalDateTime.now();
    }
}
