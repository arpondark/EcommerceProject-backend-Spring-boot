package com.arpon007.EcommerceProject.repositories;

import com.arpon007.EcommerceProject.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

}
