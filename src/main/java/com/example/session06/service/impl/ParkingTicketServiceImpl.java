package com.example.session06.service.impl;

import com.example.session06.mapper.TicketParkingMapper;
import com.example.session06.model.dto.ticket.TicketRequest;
import com.example.session06.model.dto.ticket.TicketResponse;
import com.example.session06.model.dto.ticket.TicketSummaryResponse;
import com.example.session06.model.entity.ParkingTicket;
import com.example.session06.model.entity.Vehicle;
import com.example.session06.model.entity.Zone;
import com.example.session06.repository.IPakingTicketRepository;
import com.example.session06.repository.IVehicleRepository;
import com.example.session06.repository.IZoneRepository;
import com.example.session06.service.IParkingTicketService;
import com.example.session06.util.PageResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingTicketServiceImpl implements IParkingTicketService {
    private final IPakingTicketRepository pakingTicketRepository;
    private final IVehicleRepository vehicleRepository;
    private final IZoneRepository zoneRepository;
    private final TicketParkingMapper ticketParkingMapper;

    @Transactional
    @Override
    public TicketResponse checkIn(TicketRequest request) {
        // Check vehicle
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle Not Found"));
        // Check Zone
        Zone zone = zoneRepository.findById(request.getZoneId())
                .orElseThrow(() -> new RuntimeException("Zone Not Found"));
        // Check occupiedSpots
        if (zone.getOccupiedSpots() > zone.getCapacity()) {
            throw new RuntimeException("Zone Capacity Exceeded");
        }
        // Create Ticket
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setVehicle(vehicle);
        parkingTicket.setZone(zone);

        // Save To Entity
        ParkingTicket saved = pakingTicketRepository.save(parkingTicket);
        // Update occupiedSpots
        zone.setOccupiedSpots(zone.getOccupiedSpots() + 1);
        // Map to DTO
        return ticketParkingMapper.toDTO(saved);
    }

    @Transactional
    @Override
    public TicketResponse checkOut(Long vehicleId) {
        // tìm vé gần nhất mà chưa có check out time
        ParkingTicket parkingTicket = pakingTicketRepository.findTopByVehicle_VehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle Not Found"));
        // Cập nhật check out time
        parkingTicket.setCheckOutTime(LocalDateTime.now());
        // Cập nhật lại occupiedSpots của Zone
        Zone zone = parkingTicket.getZone();
        zone.setOccupiedSpots(zone.getOccupiedSpots() - 1);
        // Map sang dto
        ParkingTicket saved = pakingTicketRepository.save(parkingTicket);
        return ticketParkingMapper.toDTO(saved);
    }

    @Override
    public List<TicketSummaryResponse> findTodayTicketSumary() {
        // call repo
        List<TicketSummaryResponse> ticketSummaryResponses = pakingTicketRepository.findTodayTicketSumary();
        return ticketSummaryResponses;
    }

    @Override
    public PageResponse<TicketResponse> findHistoryParking(int page, int size, String licensePlate, LocalDateTime fromDate, LocalDateTime toDate) {
        // Call page
        Pageable pageable = PageRequest.of(page, size);
        // call repo
        Page<TicketResponse> responsePage = pakingTicketRepository.searchAllByVehicle_LicensePlateAndCheckInTimeBetween(licensePlate,fromDate,toDate, pageable);
        // Map -> DTO
        Page<TicketResponse> ticketResponsePage = responsePage.map(ticket -> new TicketResponse(
                ticket.getParkingTicketId(),
                ticket.getLicensePlate(),
                ticket.getZoneName(),
                ticket.getCheckInTime(),
                ticket.getCheckOutTime()
        ));
        // Map -> PageResponse
        PageResponse<TicketResponse> pageResponse = new PageResponse<>();
        pageResponse.setItems(ticketResponsePage.getContent());
        pageResponse.setPage(ticketResponsePage.getNumber());
        pageResponse.setSize(ticketResponsePage.getSize());
        pageResponse.setTotalItems(ticketResponsePage.getTotalElements());
        pageResponse.setTotalPages(ticketResponsePage.getTotalPages());
        pageResponse.setIsLast(ticketResponsePage.isLast());
        return pageResponse;
    }
}
