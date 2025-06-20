package com.carwash.order_service.model;

import io.swagger.v3.oas.annotations.media.Schema;

public enum OrderStatus {
    @Schema(description = "Order is pending")
    PENDING,

    @Schema(description = "Order is completed")
    COMPLETED,

    @Schema(description = "Order has been cancelled")
    CANCELLED,

    @Schema(description = "Order is in progress")
    IN_PROGRESS
}
