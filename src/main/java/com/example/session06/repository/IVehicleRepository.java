package com.example.session06.repository;

import com.example.session06.model.dto.vehicle.VehicleResponse;
import com.example.session06.model.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {

    // Tim kiem theo tu khoa = licensePlate ( tim kiem thong qua bien so xe )
    @Query("select new com.example.session06.model.dto.vehicle.VehicleResponse(v.vehicleId, v.licensePlate, v.color, v.type) FROM Vehicle v " +
            "WHERE (lower(v.licensePlate) like lower(concat('%', :keyword , '%')) OR :keyword is null)")
    Page<VehicleResponse> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
