package com.arpon007.EcommerceProject.controller;


import com.arpon007.EcommerceProject.Utils.AuthUtil;
import com.arpon007.EcommerceProject.model.User;
import com.arpon007.EcommerceProject.payload.AddressDTO;
import com.arpon007.EcommerceProject.service.AddressService;
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

import java.util.List;


@RestController
@RequestMapping("/api")
@Tag(name = "Address APIs", description = "APIs for managing user addresses")
public class AddressController {

    @Autowired
    AuthUtil authUtil;

    @Autowired
    AddressService addressService;

    @Operation(
            summary = "Create a new address",
            description = "Creates a new address for the currently authenticated user"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Address created successfully", content = @Content(schema = @Schema(implementation = AddressDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid address data", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> createAddress(
            @Parameter(description = "Address object that needs to be created", required = true)
            @Valid @RequestBody AddressDTO addressDTO){
        User user = authUtil.loggedInUser();
        AddressDTO savedAddressDTO = addressService.createAddress(addressDTO, user);
        return new ResponseEntity<>(savedAddressDTO, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all addresses",
            description = "Retrieves a list of all addresses in the system"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all addresses"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAddresses(){
        List<AddressDTO> addressList = addressService.getAddresses();
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @Operation(
            summary = "Get address by ID",
            description = "Retrieves a specific address by its ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the address", content = @Content(schema = @Schema(implementation = AddressDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required", content = @Content),
            @ApiResponse(responseCode = "404", description = "Address not found with the given ID", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> getAddressById(
            @Parameter(description = "ID of the address to retrieve", required = true)
            @PathVariable Long addressId){
        AddressDTO addressDTO = addressService.getAddressesById(addressId);
        return new ResponseEntity<>(addressDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Get current user's addresses",
            description = "Retrieves all addresses belonging to the currently authenticated user"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user's addresses"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/users/addresses")
    public ResponseEntity<List<AddressDTO>> getUserAddresses(){
        User user = authUtil.loggedInUser();
        List<AddressDTO> addressList = addressService.getUserAddresses(user);
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @Operation(
            summary = "Update an address",
            description = "Updates an existing address with the provided details"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Address updated successfully", content = @Content(schema = @Schema(implementation = AddressDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid address data", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required", content = @Content),
            @ApiResponse(responseCode = "404", description = "Address not found with the given ID", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> updateAddress(
            @Parameter(description = "ID of the address to update", required = true)
            @PathVariable Long addressId,
            @Parameter(description = "Updated address object", required = true)
            @RequestBody AddressDTO addressDTO){
        AddressDTO updatedAddress = addressService.updateAddress(addressId, addressDTO);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete an address",
            description = "Deletes an address by its ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Address deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required", content = @Content),
            @ApiResponse(responseCode = "404", description = "Address not found with the given ID", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<String> deleteAddress(
            @Parameter(description = "ID of the address to delete", required = true)
            @PathVariable Long addressId){
        String status = addressService.deleteAddress(addressId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
