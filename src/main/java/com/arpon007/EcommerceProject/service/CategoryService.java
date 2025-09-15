package com.arpon007.EcommerceProject.service;



import com.arpon007.EcommerceProject.payload.CategoryDTO;
import com.arpon007.EcommerceProject.payload.CategoryResponse;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO category, Long categoryId);
}
