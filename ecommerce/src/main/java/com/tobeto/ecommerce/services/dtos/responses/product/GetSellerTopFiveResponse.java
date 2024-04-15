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
    private int productId;

    private int totalQuantity;
    public GetSellerTopFiveResponse(Integer productId, int totalQuantity) {
        this.productId = productId;
        this.totalQuantity = totalQuantity; // Long'u int'e dönüştür
    }
}
