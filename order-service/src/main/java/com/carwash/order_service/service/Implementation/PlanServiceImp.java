package com.carwash.order_service.service.Implementation;


import com.carwash.order_service.dto.PlanDTO;
import com.carwash.order_service.exception.ResourceNotFoundException;
import com.carwash.order_service.model.Plan;
import com.carwash.order_service.repository.PlanDAO;
import com.carwash.order_service.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanServiceImp implements PlanService {

    private final PlanDAO planDAO;

    public PlanServiceImp(PlanDAO planDAO) {
        this.planDAO = planDAO;
    }

    @Override
    public PlanDTO createPackage(PlanDTO dto) {
        Plan pkg = new Plan(null, dto.getPackageName(), dto.getDescription(),
                dto.getPrice(), dto.getCreatedBy());
        return toDTO(planDAO.save(pkg));
    }

    @Override
    public List<PlanDTO> getAllPackages() {
        return planDAO.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public PlanDTO getPackageById(Long id) {
        Plan pkg = planDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));
        return toDTO(pkg);
    }

    private PlanDTO toDTO(Plan p) {
        return new PlanDTO(p.getId(), p.getPackageName(), p.getDescription(),
                p.getPrice(), p.getCreatedBy());
    }
}
