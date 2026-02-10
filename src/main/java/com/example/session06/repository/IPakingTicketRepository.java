package com.example.session06.repository;

import com.example.session06.model.dto.ticket.TicketResponse;
import com.example.session06.model.dto.ticket.TicketSummaryResponse;
import com.example.session06.model.entity.ParkingTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IPakingTicketRepository extends JpaRepository<ParkingTicket, Long> {

    Optional<ParkingTicket> findTopByVehicle_VehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(Long vehicleId);

    @Query("select new com.example.session06.model.dto.ticket.TicketSummaryResponse(" +
            "p.parkingTicketId," +
            "p.vehicle.licensePlate," +
            "p.zone.zoneName," +
            "p.checkInTime," +
            "p.checkOutTime)" +
            "from ParkingTicket p where function('date', p.checkInTime) = current_date")
        //Func ở đây để chuyển kiểu LocaDateTime -> Date map vs CurrentDat
    List<TicketSummaryResponse> findTodayTicketSumary();

    @Query("""
    select new com.example.session06.model.dto.ticket.TicketResponse(
    p.parkingTicketId,
    p.vehicle.licensePlate,
    p.zone.zoneName,
    p.checkInTime,
    p.checkOutTime
    )
    from ParkingTicket p
    where p.vehicle.licensePlate = :licensePlate
    and p.checkInTime BETWEEN :fromDate and :toDate
    order by p.checkInTime desc
""")
    Page<TicketResponse> searchAllByVehicle_LicensePlateAndCheckInTimeBetween(@Param("licensePlate") String licensePlate, @Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate, Pageable pageable);
}
