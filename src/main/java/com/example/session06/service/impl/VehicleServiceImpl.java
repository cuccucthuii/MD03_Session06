package com.example.session06.service.impl;

import com.example.session06.mapper.VehicleMapper;
import com.example.session06.model.dto.vehicle.VehicleRequest;
import com.example.session06.model.dto.vehicle.VehicleResponse;
import com.example.session06.model.entity.Vehicle;
import com.example.session06.repository.IVehicleRepository;
import com.example.session06.service.IVehicleService;
import com.example.session06.util.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements IVehicleService {
    private final IVehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    public PageResponse<VehicleResponse> getPagedVehicles(int page, int size, String sortBy, String direction, String keyword) {
        //Nếu page < 0, mặc định về 0.
        if (page < 0) {
            page = 0;
        }
        //Nếu không có sortBy hoặc direction thì dùng không sắp xếp (unsorted).
        Sort sort = Sort.unsorted();
        //Sử dụng Sort.by(sortBy) kết hợp với direction (ASC/DESC) để tạo đối tượng Sort.
        if (sortBy != null && direction != null) {
            try {
                Sort.Direction sortDirection = Sort.Direction.fromString(direction);
                sort = Sort.by(sortDirection, sortBy);
            } catch (Exception e) {
                e.printStackTrace(); // Bat loi neu Direction / asc - desc
            }
        }

        //Sử dụng PageRequest.of(page, size, sort) để tạo đối tượng Pageable.
        Pageable pageRequest = PageRequest.of(page, size, sort);
        //Gọi vehicleRepository.findAllByKeyword(keyword, pageable) để lấy dữ liệu.
        Page<VehicleResponse> pages = vehicleRepository.findAllByKeyword(keyword, pageRequest);

        // Map sang Page Response
        PageResponse<VehicleResponse> pageResponse = new PageResponse<>();
        pageResponse.setItems(pages.getContent());
        pageResponse.setPage(pages.getNumber());
        pageResponse.setSize(pages.getSize());
        pageResponse.setTotalItems(pages.getTotalElements());
        pageResponse.setTotalPages(pages.getTotalPages());
        pageResponse.setIsLast(pages.isLast());
        return pageResponse;
    }

    @Override
    public VehicleResponse createVehicle(VehicleRequest vehicleRequest) {
           Vehicle vehicle = vehicleMapper.toEntity(vehicleRequest);
           Vehicle saved = vehicleRepository.save(vehicle);
           return vehicleMapper.toResponse(saved);
    }
}
