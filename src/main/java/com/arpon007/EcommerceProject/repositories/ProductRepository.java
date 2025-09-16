package com.arpon007.EcommerceProject.repositories;

import com.arpon007.EcommerceProject.model.Category;
import com.arpon007.EcommerceProject.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryOrderByPriceAsc(Category category);

    @Query("SELECT p FROM Product p WHERE p.category = :category ORDER BY p.price ASC")
    Page<Product> findByCategoryOrderByPriceAsc(@Param("category") Category category, Pageable pageable);

    List<Product> findByProductNameContainingIgnoreCase(String productName);

    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :productName, '%'))")
    Page<Product> findByProductNameContainingIgnoreCase(@Param("productName") String productName, Pageable pageable);
}
