package com.arpon007.EcommerceProject.controller;

import com.arpon007.EcommerceProject.Utils.AuthUtil;
import com.arpon007.EcommerceProject.model.Cart;
import com.arpon007.EcommerceProject.payload.CartDTO;
import com.arpon007.EcommerceProject.repositories.CartRepository;
import com.arpon007.EcommerceProject.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Cart APIs", description = "APIs for managing shopping carts")
public class CartController {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    AuthUtil authUtil;


    @Autowired
    private CartService cartService;

    @Operation(
            summary = "Add product to cart",
            description = "Adds a product with specified quantity to the current user's cart"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product added to cart successfully", content = @Content(schema = @Schema(implementation = CartDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid product or quantity", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/carts/products/{productId}/quantity/{quantity}")
    public ResponseEntity<CartDTO> addProductToCart(
            @Parameter(description = "ID of the product to add", required = true) @PathVariable Long productId,
            @Parameter(description = "Quantity of the product to add", required = true) @PathVariable Integer quantity) {
        CartDTO cartDTO = cartService.addProductToCart(productId, quantity);
        return new ResponseEntity<CartDTO>(cartDTO, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all carts",
            description = "Retrieves a list of all shopping carts in the system"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "302", description = "Successfully retrieved all carts"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/carts")
    public ResponseEntity<List<CartDTO>> getCarts() {
        List<CartDTO> cartDTOs = cartService.getAllCarts();
        return new ResponseEntity<List<CartDTO>>(cartDTOs, HttpStatus.FOUND);
    }

    @Operation(
            summary = "Get current user's cart",
            description = "Retrieves the shopping cart for the currently authenticated user"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved cart", content = @Content(schema = @Schema(implementation = CartDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cart not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("carts/users/cart")
    public ResponseEntity<CartDTO> getCartById() {
        String emailId = authUtil.loggedInEmail();
        Cart cart = cartRepository.findCartByEmail(emailId);
        Long cartId = cart.getCartId();
        CartDTO cartDTO = cartService.getCart(emailId, cartId);
        return new ResponseEntity<CartDTO>(cartDTO, HttpStatus.OK);

    }

    @Operation(
            summary = "Update product quantity in cart",
            description = "Updates the quantity of a product in the cart. Use 'delete' operation to decrease quantity by 1, or any other value to increase by 1"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product quantity updated successfully", content = @Content(schema = @Schema(implementation = CartDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid operation", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found in cart", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/cart/products/{productId}/quantity/{operation}")
    public ResponseEntity<CartDTO> updateCartProduct(
            @Parameter(description = "ID of the product to update", required = true) @PathVariable Long productId,
            @Parameter(description = "Operation: 'delete' to decrease, anything else to increase", required = true) @PathVariable String operation) {

        CartDTO cartDTO = cartService.updateProductQuantityInCart(productId, operation.equalsIgnoreCase("delete") ? -1 : 1);
        return new ResponseEntity<CartDTO>(cartDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete product from cart",
            description = "Removes a specific product from the cart completely"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product deleted from cart successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cart or product not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/carts/{cartId}/product/{productId}")
    public ResponseEntity<String> deleteProductFromCart(
            @Parameter(description = "ID of the cart", required = true) @PathVariable Long cartId,
            @Parameter(description = "ID of the product to remove", required = true) @PathVariable Long productId) {
        String status = cartService.deleteProductFromCart(cartId, productId);
        return new ResponseEntity<String>(status, HttpStatus.OK);
    }
}
