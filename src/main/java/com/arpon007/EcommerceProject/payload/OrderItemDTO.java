package com.arpon007.EcommerceProject.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Order Item Data Transfer Object")
public class OrderItemDTO {
    @Schema(description = "Unique identifier of the order item", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long orderItemId;

    @Schema(description = "Product details")
    private ProductDTO product;

    @Schema(description = "Quantity of the product ordered", example = "2")
    private Integer quantity;

    @Schema(description = "Discount applied on this item", example = "50.00")
    private double discount;

    @Schema(description = "Price of the product at the time of order", example = "899.99")
    private double orderedProductPrice;
}
