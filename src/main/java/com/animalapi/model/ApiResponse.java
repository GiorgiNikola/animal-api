package com.animalapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ApiResponse", description = "Standard API response format")
public class ApiResponse {
    private int code;
    private String type;
    private String message;
}
