package com.arpon007.EcommerceProject.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Paginated Category Response")
public class CategoryResponse {
    @Schema(description = "List of categories in the current page")
    private List<CategoryDTO> content;

    @Schema(description = "Current page number (zero-based)", example = "0")
    private Integer pageNumber;

    @Schema(description = "Number of items per page", example = "5")
    private Integer pageSize;

    @Schema(description = "Total number of categories", example = "25")
    private Long totalElements;

    @Schema(description = "Total number of pages", example = "5")
    private Integer totalPages;

    @Schema(description = "Whether this is the last page", example = "true")
    private Boolean lastPage;
}
