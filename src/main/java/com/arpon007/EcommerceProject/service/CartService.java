package com.arpon007.EcommerceProject.service;

import com.arpon007.EcommerceProject.payload.CartDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CartService {
    CartDTO addProductToCart(Long productId, Integer quantity);

    List<CartDTO> getAllCarts();

    CartDTO getCart(String emailId, Long cartId);

    @Transactional
    CartDTO updateProductQuantityInCart(Long productId, Integer delete);

    String deleteProductFromCart(Long cartId, Long productId);
}
