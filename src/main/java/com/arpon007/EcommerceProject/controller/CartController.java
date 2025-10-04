package com.arpon007.EcommerceProject.controller;

import com.arpon007.EcommerceProject.Utils.AuthUtil;
import com.arpon007.EcommerceProject.model.Cart;
import com.arpon007.EcommerceProject.payload.CartDTO;
import com.arpon007.EcommerceProject.payload.CartItemsDTO;
import com.arpon007.EcommerceProject.repositories.CartRepository;
import com.arpon007.EcommerceProject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    AuthUtil authUtil;


    @Autowired
    private CartService cartService;

    @PostMapping("/carts/products/{productId}/quantity/{quantity}")
    public ResponseEntity<CartDTO> addProductToCart(@PathVariable Long productId,@PathVariable Integer quantity) {
        CartDTO cartDTO = cartService.addProductToCart(productId, quantity);
        return new ResponseEntity<CartDTO>(cartDTO, HttpStatus.CREATED);
    }

    @GetMapping("/carts")
    public ResponseEntity<List<CartDTO>> getCarts() {
        List<CartDTO> cartDTOs = cartService.getAllCarts();
        return new ResponseEntity<List<CartDTO>>(cartDTOs, HttpStatus.FOUND);
    }

    @GetMapping("carts/users/cart")
    public ResponseEntity<CartDTO> getCartById() {
        String emailId = authUtil.loggedInEmail();
        Cart cart = cartRepository.findCartByEmail(emailId);
        Long cartId = cart.getCartId();
       CartDTO cartDTO = cartService.getCart(emailId,cartId);
       return new ResponseEntity<CartDTO>(cartDTO,HttpStatus.OK);

    }


}
