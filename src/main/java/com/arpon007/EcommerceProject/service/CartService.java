package com.arpon007.EcommerceProject.service;

import com.arpon007.EcommerceProject.payload.CartDTO;

public interface CartService {
    CartDTO addProductToCart(Long productId, Integer quantity);
}
