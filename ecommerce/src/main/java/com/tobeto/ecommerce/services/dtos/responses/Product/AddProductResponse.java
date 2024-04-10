package com.tobeto.ecommerce.services.dtos.responses.Product;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddProductResponse {

    private int id;

    private String name;

    private String description;

    private double unitPrice;

    private int stockAmount;

    private String categoryName;
}