package com.arpon007.EcommerceProject.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Paginated Product Response")
public class ProductResponse {
    @Schema(description = "List of products in the current page")
    private List<ProductDTO> content;

    @Schema(description = "Current page number (zero-based)", example = "0")
    private Integer pageNumber;

    @Schema(description = "Number of items per page", example = "10")
    private Integer pageSize;

    @Schema(description = "Total number of products", example = "100")
    private Long totalElements;

    @Schema(description = "Total number of pages", example = "10")
    private Integer totalPages;

    @Schema(description = "Whether this is the last page", example = "false")
    private boolean lastPage;
}
