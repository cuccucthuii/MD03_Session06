package com.example.session06.model.dto.ticket;

import com.example.session06.model.entity.Vehicle;
import com.example.session06.model.entity.Zone;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class TicketRequest {
    private Long vehicleId;
    private Long zoneId;
}
