package com.arpon007.EcommerceProject.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Schema(description = "Product Data Transfer Object")
public class ProductDTO {
    @Schema(description = "Unique identifier of the product", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long productId;

    @Schema(description = "Name of the product", example = "iPhone 15 Pro", requiredMode = Schema.RequiredMode.REQUIRED)
    private String productName;

    @Schema(description = "Detailed description of the product", example = "Latest iPhone with A17 Pro chip")
    private String description;

    @Schema(description = "URL or path to the product image", example = "https://example.com/images/iphone15.jpg")
    private String image;

    @Schema(description = "Available quantity in stock", example = "50", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer quantity;

    @Schema(description = "Original price of the product", example = "999.99", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double price;

    @Schema(description = "Discount amount on the product", example = "100.00")
    private Double discountPrice;

    @Schema(description = "Final price after discount", example = "899.99", accessMode = Schema.AccessMode.READ_ONLY)
    private Double specialPrice;

    @Schema(description = "ID of the category this product belongs to", example = "5")
    private Long categoryId;
}
