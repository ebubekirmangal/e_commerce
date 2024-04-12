package com.tobeto.ecommerce.services.dtos.responses.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllOrderResponse {

    private int id;

    private int productId;

    private int quantity;

    private Double unitPrice;

    private int userId;

}
