package com.arpon007.EcommerceProject.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Shopping Cart Data Transfer Object")
public class CartDTO {
    @Schema(description = "Unique identifier of the cart", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long cartId;

    @Schema(description = "Total price of all items in the cart", example = "2599.99", accessMode = Schema.AccessMode.READ_ONLY)
    private Double totalPrice=0.0;

    @Schema(description = "List of products in the cart")
    private List<ProductDTO> products = new ArrayList<>();
}
