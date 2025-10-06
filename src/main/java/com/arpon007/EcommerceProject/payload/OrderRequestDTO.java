package com.arpon007.EcommerceProject.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Order Request Data Transfer Object for creating orders")
public class OrderRequestDTO {
    @Schema(description = "ID of the delivery address", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long addressId;

    @Schema(description = "Payment method to be used", example = "Credit Card", requiredMode = Schema.RequiredMode.REQUIRED)
    private String paymentMethod;

    @Schema(description = "Name of the payment gateway", example = "Razorpay")
    private String pgName;

    @Schema(description = "Payment gateway transaction ID", example = "pg_1234567890")
    private String pgPaymentId;

    @Schema(description = "Payment gateway status", example = "SUCCESS")
    private String pgStatus;

    @Schema(description = "Response message from payment gateway", example = "Payment completed successfully")
    private String pgResponseMessage;
}
