package com.example.session06.mapper;

import com.example.session06.model.dto.ticket.TicketRequest;
import com.example.session06.model.dto.ticket.TicketResponse;
import com.example.session06.model.entity.ParkingTicket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketParkingMapper {
    // Entity -> DTO ( GET )
    @Mapping(source = "vehicle.licensePlate", target = "licensePlate")
    @Mapping(source = "zone.zoneName", target = "zoneName")
    TicketResponse toDTO(ParkingTicket parkingTicket);
    // DTO -> ENTITY ( POST )
    ParkingTicket toEntity(TicketRequest ticketRequest);
}
