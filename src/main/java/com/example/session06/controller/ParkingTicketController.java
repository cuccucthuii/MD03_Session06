package com.example.session06.controller;

import com.example.session06.model.dto.ticket.TicketRequest;
import com.example.session06.model.dto.ticket.TicketResponse;
import com.example.session06.model.dto.ticket.TicketSummaryResponse;
import com.example.session06.service.IParkingTicketService;
import com.example.session06.util.ApiResponse;
import com.example.session06.util.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/parking-ticket")
public class ParkingTicketController {
    @Autowired
    private IParkingTicketService parkingTicketService;

    @PostMapping("/check-in")
    public ResponseEntity<ApiResponse<TicketResponse>> createParkingTicket(@RequestBody TicketRequest request){
        TicketResponse response = parkingTicketService.checkIn(request);
        ApiResponse<TicketResponse> apiResponse = new ApiResponse<>(true,"Success",response);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED); //201
    }

    @PutMapping("/check-out/{vehicleId}")
    public ResponseEntity<ApiResponse<TicketResponse>> checkout(@PathVariable Long vehicleId){
        TicketResponse response = parkingTicketService.checkOut(vehicleId);
        ApiResponse<TicketResponse> apiResponse = new ApiResponse<>(true,"Success",response);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK); //200
    }

    @GetMapping("/summary")
    private ResponseEntity<ApiResponse<List<TicketSummaryResponse>>> getSummary(){
        List<TicketSummaryResponse> list = parkingTicketService.findTodayTicketSumary();
        ApiResponse<List<TicketSummaryResponse>> apiResponse = new ApiResponse<>(true,"Success",list);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);// 200
    }

    @GetMapping("/history")
    public ResponseEntity<ApiResponse<PageResponse<TicketResponse>>> getHistory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String licensePlate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") LocalDateTime fromDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") LocalDateTime toDate){
        PageResponse<TicketResponse> pageResponse = parkingTicketService.findHistoryParking(page,size,licensePlate,fromDate,toDate);
        ApiResponse<PageResponse<TicketResponse>> apiResponse = new ApiResponse<>(true,"Success",pageResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);//200
    }
}
