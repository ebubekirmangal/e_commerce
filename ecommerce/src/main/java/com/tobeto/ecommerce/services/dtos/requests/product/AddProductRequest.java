package com.tobeto.ecommerce.services.dtos.requests.product;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddProductRequest {

    private String name;

    private String description;

    @Min(value = 0,message = "'unitPrice' 0'dan büyük olmalı.")
    private double unitPrice;
    @Min(value = 0,message = "'stockAmount' 0'dan küçük olmamalı.")
    private int stockAmount;

    private int categoryId;



}
