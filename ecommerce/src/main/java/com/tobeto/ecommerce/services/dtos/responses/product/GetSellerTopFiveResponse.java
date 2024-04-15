package com.tobeto.ecommerce.services.dtos.responses.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetSellerTopFiveResponse {
    private Integer productId;

    private String name;

    private String description;

    private double unitPrice;

    private String categoryName;

    private Long totalQuantity;
}