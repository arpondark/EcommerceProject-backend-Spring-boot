package com.arpon007.EcommerceProject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "Health APIs", description = "APIs for health check and monitoring")
public class HealthController {

    @Operation(
            summary = "Health check",
            description = "Returns the health status of the application with timestamp and version information"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Service is up and running")
    })
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("timestamp", LocalDateTime.now());
        health.put("service", "EcommerceProject");
        health.put("version", "0.0.1-SNAPSHOT");

        return new ResponseEntity<>(health, HttpStatus.OK);
    }

    @Operation(
            summary = "Ping endpoint",
            description = "Simple ping endpoint to check if the service is responsive"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Service responded with pong")
    })
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
