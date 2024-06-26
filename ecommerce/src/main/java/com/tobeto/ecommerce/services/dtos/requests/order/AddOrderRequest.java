package com.tobeto.ecommerce.services.dtos.requests.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddOrderRequest {
    private int userId;
    private LocalDateTime orderDate;
    private List<OrderProductRequest> orderProductRequest;
}
