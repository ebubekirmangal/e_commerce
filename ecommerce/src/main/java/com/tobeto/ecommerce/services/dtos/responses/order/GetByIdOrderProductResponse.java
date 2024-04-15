package com.tobeto.ecommerce.services.dtos.responses.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetByIdOrderProductResponse {

    private int orderId;

    private int productId;

    private String productName;

    private int quantity;

    private Double totalPrice;

    private int userId;

    private String userFirstName;

    private String userLastName;
}
