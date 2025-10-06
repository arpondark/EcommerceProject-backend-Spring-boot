package com.arpon007.EcommerceProject.controller;


import com.arpon007.EcommerceProject.Utils.AuthUtil;
import com.arpon007.EcommerceProject.payload.OrderDTO;
import com.arpon007.EcommerceProject.payload.OrderRequestDTO;
import com.arpon007.EcommerceProject.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Order APIs", description = "APIs for managing orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthUtil authUtil;

    @Operation(
            summary = "Place an order",
            description = "Creates a new order for the authenticated user with the specified payment method and address"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Order placed successfully", content = @Content(schema = @Schema(implementation = OrderDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid order data or payment information", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required", content = @Content),
            @ApiResponse(responseCode = "404", description = "Address not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/order/users/payments/{paymentMethod}")
    public ResponseEntity<OrderDTO> orderProducts(
            @Parameter(description = "Payment method for the order", required = true) @PathVariable String paymentMethod,
            @Parameter(description = "Order request containing address and payment details", required = true) @RequestBody OrderRequestDTO orderRequestDTO) {
        String emailId = authUtil.loggedInEmail();
        OrderDTO order = orderService.placeOrder(
                emailId,
                orderRequestDTO.getAddressId(),
                paymentMethod,
                orderRequestDTO.getPgName(),
                orderRequestDTO.getPgPaymentId(),
                orderRequestDTO.getPgStatus(),
                orderRequestDTO.getPgResponseMessage()
        );
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
