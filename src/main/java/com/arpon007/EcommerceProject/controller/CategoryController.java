package com.arpon007.EcommerceProject.controller;

import com.arpon007.EcommerceProject.config.AppConstants;
import com.arpon007.EcommerceProject.payload.CategoryDTO;
import com.arpon007.EcommerceProject.payload.CategoryResponse;
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

import com.arpon007.EcommerceProject.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Tag(name = "Category APIs", description = "APIs for managing product categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(
            summary = "Get all categories",
            description = "Retrieves a paginated list of all categories with sorting options"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved categories"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid parameters", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(
            @Parameter(description = "Page number for pagination (starts from 0)") @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @Parameter(description = "Number of items per page") @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @Parameter(description = "Field name to sort by") @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            @Parameter(description = "Sort direction (asc or desc)") @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {
        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Create a new category",
            description = "Creates a new category with the provided details"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Category created successfully", content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid category data or category already exists", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(
            @Parameter(description = "Category object that needs to be created", required = true)
            @Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO saveCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(saveCategoryDTO, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Delete a category",
            description = "Deletes a category by its ID. Only accessible by admin users."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category deleted successfully", content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized, admin authentication required", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found with the given ID", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(
            @Parameter(description = "ID of the category to delete", required = true)
            @PathVariable Long categoryId) {
        CategoryDTO deleteCategory = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(deleteCategory, HttpStatus.OK);
    }

    @Operation(
            summary = "Update a category",
            description = "Updates an existing category with the provided details"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category updated successfully", content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid category data", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required", content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found with the given ID", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(
            @Parameter(description = "Updated category object", required = true)
            @Valid @RequestBody CategoryDTO categoryDTO,
            @Parameter(description = "ID of the category to update", required = true)
            @PathVariable Long categoryId) {
        CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO, categoryId);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);
    }
}
