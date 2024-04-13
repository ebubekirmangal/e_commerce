package com.tobeto.ecommerce.services.dtos.responses.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTopSellingProductResponse {
    private int id;

    private String name;

    private int salesCount;

    private String description;

    private double unitPrice;

    private int stockAmount;

    private String categoryName;

}
