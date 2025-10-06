package com.arpon007.EcommerceProject.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Cart Item Data Transfer Object")
public class CartItemsDTO {
    @Schema(description = "Unique identifier of the cart item", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long cartItemId;

    @Schema(description = "Cart that contains this item")
    private CartDTO cart;

    @Schema(description = "Product details")
    private ProductDTO product;

    @Schema(description = "Quantity of the product in cart", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer quantity;

    @Schema(description = "Discount applied on this item", example = "50.00")
    private Double discount;

    @Schema(description = "Price of the product", example = "999.99")
    private Double productPrice;
}
