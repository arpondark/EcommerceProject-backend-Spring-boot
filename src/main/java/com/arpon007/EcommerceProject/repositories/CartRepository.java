package com.arpon007.EcommerceProject.repositories;

import com.arpon007.EcommerceProject.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart,Long> {


    @Query("select c from Cart c where c.user.email = ?1")
    Cart findCartByEmail(String email);
}
