package com.arpon007.EcommerceProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arpon007.EcommerceProject.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategoryName(String categoryName);
}
