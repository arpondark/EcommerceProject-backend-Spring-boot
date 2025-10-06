package com.arpon007.EcommerceProject.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Address Data Transfer Object")
public class AddressDTO {
    @Schema(description = "Unique identifier of the address", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long addressId;

    @Schema(description = "Street name and number", example = "123 Main Street", requiredMode = Schema.RequiredMode.REQUIRED)
    private String street;

    @Schema(description = "Building or apartment name", example = "Sunrise Apartments")
    private String buildingName;

    @Schema(description = "City name", example = "New York", requiredMode = Schema.RequiredMode.REQUIRED)
    private String city;

    @Schema(description = "State or province", example = "NY", requiredMode = Schema.RequiredMode.REQUIRED)
    private String state;

    @Schema(description = "Country name", example = "USA", requiredMode = Schema.RequiredMode.REQUIRED)
    private String country;

    @Schema(description = "Postal/ZIP code", example = "10001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String pincode;
}
