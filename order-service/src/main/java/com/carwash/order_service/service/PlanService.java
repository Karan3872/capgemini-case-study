package com.carwash.order_service.service;


import com.carwash.order_service.dto.PlanDTO;
import java.util.List;

public interface PlanService {
    PlanDTO createPackage(PlanDTO dto);
    List<PlanDTO> getAllPackages();
    PlanDTO getPackageById(Long id);
}
