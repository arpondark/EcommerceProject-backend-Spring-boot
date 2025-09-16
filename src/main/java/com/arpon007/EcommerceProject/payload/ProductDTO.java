package com.arpon007.EcommerceProject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productName;
    private String image;
    private Integer quantity;
    private Double price;
    private Double discountPrice;
    private Double specialPrice;
    private Long categoryId;
}
