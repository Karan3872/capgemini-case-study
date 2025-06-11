package com.carwash.order_service.service;


import com.carwash.order_service.dto.OrderDTO;
import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO dto);
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(Long id);
}
