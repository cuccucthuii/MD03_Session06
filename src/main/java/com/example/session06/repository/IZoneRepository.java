package com.example.session06.repository;

import com.example.session06.model.dto.zone.ZoneStatisticsResponse;
import com.example.session06.model.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IZoneRepository extends JpaRepository<Zone,Long> {

    @Query("""
            select new com.example.session06.model.dto.zone.ZoneStatisticsResponse(
            z.zoneId,
            z.zoneName,
            z.capacity,
            z.occupiedSpots,
            (z.capacity - z.occupiedSpots))
            from Zone z
            """)
    List<ZoneStatisticsResponse> getAvaliableZones();
}
