package com.arpon007.EcommerceProject.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Order Data Transfer Object")
public class OrderDTO {
    @Schema(description = "Unique identifier of the order", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long orderId;

    @Schema(description = "Email address of the customer", example = "customer@example.com")
    private String email;

    @Schema(description = "List of items in the order")
    private List<OrderItemDTO> orderItems;

    @Schema(description = "Date when the order was placed", example = "2025-10-06")
    private LocalDate orderDate;

    @Schema(description = "Payment details for the order")
    private PaymentDTO payment;

    @Schema(description = "Total amount of the order", example = "1999.99")
    private Double totalAmount;

    @Schema(description = "Current status of the order", example = "PENDING", allowableValues = {"PENDING", "CONFIRMED", "SHIPPED", "DELIVERED", "CANCELLED"})
    private String orderStatus;

    @Schema(description = "ID of the delivery address", example = "1")
    private Long addressId;
}
