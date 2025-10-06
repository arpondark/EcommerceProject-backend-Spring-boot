package com.arpon007.EcommerceProject.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Payment Data Transfer Object")
public class PaymentDTO {
    @Schema(description = "Unique identifier of the payment", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long paymentId;

    @Schema(description = "Payment method used", example = "Credit Card", allowableValues = {"Credit Card", "Debit Card", "UPI", "Net Banking", "Cash on Delivery"})
    private String paymentMethod;

    @Schema(description = "Payment gateway transaction ID", example = "pg_1234567890")
    private String pgPaymentId;

    @Schema(description = "Payment gateway status", example = "SUCCESS", allowableValues = {"SUCCESS", "PENDING", "FAILED"})
    private String pgStatus;

    @Schema(description = "Response message from payment gateway", example = "Payment completed successfully")
    private String pgResponseMessage;

    @Schema(description = "Name of the payment gateway", example = "Razorpay")
    private String pgName;
}
