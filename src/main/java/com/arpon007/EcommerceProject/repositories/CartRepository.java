package com.arpon007.EcommerceProject.repositories;

import com.arpon007.EcommerceProject.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {


    @Query("select c from Cart c where c.user.email = ?1")
    Cart findCartByEmail(String email);

    @Query("select c from Cart c where c.user.email = ?1 and c.cartId = ?2")
    Cart findCartByEmailAndCartId(String emailId, Long cartId);

    @Query("SELECT c FROM Cart c JOIN FETCH c.items ci JOIN FETCH ci.product p WHERE p.productId = ?1")
    List<Cart> findCartsByProductId(Long productId);
}
