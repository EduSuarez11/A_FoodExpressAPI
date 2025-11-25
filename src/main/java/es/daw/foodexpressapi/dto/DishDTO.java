package es.daw.foodexpressapi.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DishDTO {

    // ===== NAME =====
    @NotBlank(message = "Dish name is required")
    @Size(min = 2, max = 100, message = "Dish name must be between 2 and 100 characters")
    @Pattern(
            regexp = "^[A-Za-z0-9ÁÉÍÓÚáéíóúñÑüÜ '()-]{2,100}$",
            message = "Dish name contains invalid characters"
    )
    private String name;

    // ===== PRICE =====
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be at least 0.01")
    @DecimalMax(value = "999.99", message = "Price must not exceed 999.99")
    // Para asegurar que no mandan números excesivos tipo 123456789...
    @Digits(integer = 3, fraction = 2, message = "Price must have max 3 digits and 2 decimals")
    private BigDecimal price;

    // ===== CATEGORY =====
    @NotBlank(message = "Category is required")
    @Size(min = 2, max = 50, message = "Category must be between 2 and 50 characters")
    // Esta expresión evita caracteres peligrosos y permite categorías comunes
    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúñÑüÜ /'-]{2,50}$",
            message = "Category contains invalid characters"
    )
    private String category;

    // ===== RESTAURANT NAME =====
    @NotBlank(message = "Restaurant name is required")
    @Size(min = 2, max = 100, message = "Restaurant name must be between 2 and 100 characters")
    @Pattern(
            regexp = "^[A-Za-z0-9ÁÉÍÓÚáéíóúñÑüÜ '()-]{2,100}$",
            message = "Restaurant name contains invalid characters"
    )
    private String restaurantName;
}

