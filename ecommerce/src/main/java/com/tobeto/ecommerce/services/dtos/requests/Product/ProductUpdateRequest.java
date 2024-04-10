package com.tobeto.ecommerce.services.dtos.requests.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequest {

    private String name;

    private String description;

    private double unitPrice;

    private int stockAmount;

    private String categoryID;
}
