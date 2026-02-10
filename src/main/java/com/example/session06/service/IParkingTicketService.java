package com.example.session06.service;

import com.example.session06.model.dto.ticket.TicketRequest;
import com.example.session06.model.dto.ticket.TicketResponse;
import com.example.session06.model.dto.ticket.TicketSummaryResponse;
import com.example.session06.util.PageResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface IParkingTicketService {
    // checkin
    TicketResponse checkIn(TicketRequest request);
    // checkout
    TicketResponse checkOut(Long vehicleId);
    // Sumary ticket
    List<TicketSummaryResponse>  findTodayTicketSumary();
    // History parking
    PageResponse<TicketResponse> findHistoryParking(int page, int size, String licensePlate, LocalDateTime fromDate, LocalDateTime toDate);
}
