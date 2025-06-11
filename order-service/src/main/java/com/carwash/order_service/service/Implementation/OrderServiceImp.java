package com.carwash.order_service.service.Implementation;


import com.carwash.order_service.dto.OrderDTO;
import com.carwash.order_service.exception.ResourceNotFoundException;
import com.carwash.order_service.model.*;
import com.carwash.order_service.model.Plan;
import com.carwash.order_service.repository.*;
import com.carwash.order_service.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {

    private final OrderDAO orderDAO;
    private final PlanDAO planDAO;

    public OrderServiceImp(OrderDAO orderDAO, PlanDAO planDAO) {
        this.orderDAO = orderDAO;
        this.planDAO = planDAO;
    }

    @Override
    public OrderDTO createOrder(OrderDTO dto) {
        Plan pack = planDAO.findById(dto.getPackageId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

        Order order = new Order(dto,pack);


        order = orderDAO.save(order);
        dto.setId(order.getId());
        return dto;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderDAO.findAll().stream()
                .map(order -> new OrderDTO(order.getId(), order.getCustomerId(),
                        order.getWasherId(), order.getWashPlan().getId(),
                        order.getStatus(), order.getScheduledDate()))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return new OrderDTO(order.getId(), order.getCustomerId(),
                order.getWasherId(), order.getWashPlan().getId(),
                order.getStatus(), order.getScheduledDate());
    }
}
