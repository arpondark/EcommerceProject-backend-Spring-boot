package com.arpon007.EcommerceProject.service;

import com.arpon007.EcommerceProject.payload.CartDTO;

import java.util.List;

public interface CartService {
    CartDTO addProductToCart(Long productId, Integer quantity);

    List<CartDTO> getAllCarts();
}
