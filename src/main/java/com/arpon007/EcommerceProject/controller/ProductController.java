package com.arpon007.EcommerceProject.controller;

import com.arpon007.EcommerceProject.config.AppConstants;
import com.arpon007.EcommerceProject.payload.ProductDTO;
import com.arpon007.EcommerceProject.payload.ProductResponse;
import com.arpon007.EcommerceProject.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@Tag(name = "Product APIs", description = "APIs for managing products")
public class ProductController {
    @Autowired
    ProductService productService;

    @Operation(
            summary = "Add a new product",
            description = "Creates a new product under the specified category. Only accessible by admin users."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product created successfully", content = @Content(schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid product data", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, admin authentication required", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(
            @Parameter(description = "Product object that needs to be created", required = true) @Valid @RequestBody ProductDTO productDTO,
            @Parameter(description = "ID of the category to add the product to", required = true) @PathVariable Long categoryId){
        ProductDTO savedProductDTO = productService.addProduct(categoryId, productDTO);
        return new ResponseEntity<>(savedProductDTO, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all products",
            description = "Retrieves a paginated list of all products with sorting options"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved products"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid parameters", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts(
            @Parameter(description = "Page number for pagination (starts from 0)") @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @Parameter(description = "Number of items per page") @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @Parameter(description = "Field name to sort by") @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
            @Parameter(description = "Sort direction (asc or desc)") @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ){
        ProductResponse productResponse = productService.getAllProducts(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @Operation(
            summary = "Get products by category",
            description = "Retrieves a paginated list of products filtered by category"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved products"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid parameters", content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductsByCategory(
            @Parameter(description = "ID of the category to filter products by", required = true) @PathVariable Long categoryId,
            @Parameter(description = "Page number for pagination (starts from 0)") @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @Parameter(description = "Number of items per page") @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @Parameter(description = "Field name to sort by") @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
            @Parameter(description = "Sort direction (asc or desc)") @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder){
        ProductResponse productResponse = productService.searchByCategory(categoryId, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Search products by keyword",
            description = "Searches for products matching the specified keyword in name or description"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "302", description = "Successfully found products"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid parameters", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductsByKeyword(
            @Parameter(description = "Keyword to search for in product name or description", required = true) @PathVariable String keyword,
            @Parameter(description = "Page number for pagination (starts from 0)") @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @Parameter(description = "Number of items per page") @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @Parameter(description = "Field name to sort by") @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
            @Parameter(description = "Sort direction (asc or desc)") @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder){
        ProductResponse productResponse = productService.searchProductByKeyword(keyword, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productResponse, HttpStatus.FOUND);
    }

    @Operation(
            summary = "Update a product",
            description = "Updates an existing product with the provided details. Only accessible by admin users."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product updated successfully", content = @Content(schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid product data", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, admin authentication required", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(
            @Parameter(description = "Updated product object", required = true) @Valid @RequestBody ProductDTO productDTO,
            @Parameter(description = "ID of the product to update", required = true) @PathVariable Long productId){
        ProductDTO updatedProductDTO = productService.updateProduct(productId, productDTO);
        return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a product",
            description = "Deletes a product by its ID. Only accessible by admin users."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product deleted successfully", content = @Content(schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized, admin authentication required", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct(
            @Parameter(description = "ID of the product to delete", required = true) @PathVariable Long productId){
        ProductDTO deletedProduct = productService.deleteProduct(productId);
        return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
    }

    @Operation(
            summary = "Update product image",
            description = "Updates the image of a product"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product image updated successfully", content = @Content(schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid image file", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/products/{productId}/image")
    public ResponseEntity<ProductDTO> updateProductImage(
            @Parameter(description = "ID of the product to update image for", required = true) @PathVariable Long productId,
            @Parameter(description = "Image file to upload", required = true) @RequestParam("image") MultipartFile image) throws IOException {
        ProductDTO updatedProduct = productService.updateProductImage(productId, image);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}
