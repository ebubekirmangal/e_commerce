package com.tobeto.ecommerce.services.dtos.requests.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteProductRequest {
    private int id;
    private int userId;
}
