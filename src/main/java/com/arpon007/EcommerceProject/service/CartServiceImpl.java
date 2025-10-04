package com.arpon007.EcommerceProject.service;

import com.arpon007.EcommerceProject.Utils.AuthUtil;
import com.arpon007.EcommerceProject.exceptions.APIException;
import com.arpon007.EcommerceProject.exceptions.ResourceNotFoundException;
import com.arpon007.EcommerceProject.model.Cart;
import com.arpon007.EcommerceProject.model.CartItem;
import com.arpon007.EcommerceProject.model.Product;
import com.arpon007.EcommerceProject.payload.CartDTO;
import com.arpon007.EcommerceProject.payload.ProductDTO;
import com.arpon007.EcommerceProject.repositories.CartItemRepository;
import com.arpon007.EcommerceProject.repositories.CartRepository;
import com.arpon007.EcommerceProject.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    AuthUtil authUtil;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CartDTO addProductToCart(Long productId, Integer quantity) {

        Cart cart = createCart();
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "Product Id", productId));

        CartItem cartitem = cartItemRepository.findCartItemByProductIdAndCartId(
                cart.getCartId(), productId
        );
        if (cartitem != null) {
            throw new APIException("product" + product.getProductName() + " already exists");
        }
        if (product.getQuantity() == 0) {
            throw new APIException("product" + product.getProductName() + " is out of stock");
        }
        if (product.getQuantity() < quantity) {
            throw new APIException("Please, make an Order of the " + product.getProductName() + " less than  or equal to quantity " + product.getQuantity() + ".");
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setProduct(product);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(quantity);
        newCartItem.setDiscount(product.getDiscount());
        newCartItem.setProductPrice(product.getSpecialPrice());


        cartItemRepository.save(newCartItem);
        product.setQuantity(product.getQuantity()); // stock update
        cart.setTotalPrice(cart.getTotalPrice() + product.getSpecialPrice() * quantity);
        cartRepository.save(cart);

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

        List<CartItem> cartItems = cart.getItems();
        Stream<ProductDTO> productDTOStream = cartItems.stream().map(
                item -> {
                    ProductDTO map = modelMapper.map(item.getProduct(), ProductDTO.class);
                    map.setQuantity(item.getQuantity());
                    return map;
                });
        cartDTO.setProducts(productDTOStream.toList());


        return cartDTO;
    }

    @Override
    public List<CartDTO> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();

        if (carts.isEmpty()) {
            throw new APIException("No cart exists");
        }

        List<CartDTO> cartDTOs = carts.stream().map(cart -> {
            CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

            List<ProductDTO> products = cart.getItems().stream()
                    .map(item -> modelMapper.map(item.getProduct(), ProductDTO.class)).collect(Collectors.toList());

            cartDTO.setProducts(products);

            return cartDTO;

        }).collect(Collectors.toList());

        return cartDTOs;
    }

    private Cart createCart() {
        Cart userCart = cartRepository.findCartByEmail(authUtil.loggedInEmail());
        if (userCart != null) {
            return userCart;
        }
        Cart cart = new Cart();
        cart.setTotalPrice(0.00);
        cart.setUser(authUtil.loggedInUser());
        return cartRepository.save(cart);
    }

}
