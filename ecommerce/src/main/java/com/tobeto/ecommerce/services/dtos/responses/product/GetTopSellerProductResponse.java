package com.tobeto.ecommerce.services.dtos.responses.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetTopSellerProductResponse {

    private int id;

    private String name;

    private String description;

    private double unitPrice;

    private String categoryName;
}
