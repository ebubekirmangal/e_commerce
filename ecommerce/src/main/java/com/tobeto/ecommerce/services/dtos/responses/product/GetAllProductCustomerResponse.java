package com.tobeto.ecommerce.services.dtos.responses.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductCustomerResponse {

    private int id;

    private String name;

    private Double unitPrice;

    private String categoryName;

}
