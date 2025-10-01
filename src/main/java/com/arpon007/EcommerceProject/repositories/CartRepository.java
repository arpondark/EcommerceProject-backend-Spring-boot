package com.arpon007.EcommerceProject.repositories;

import com.arpon007.EcommerceProject.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
