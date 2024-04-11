package com.tobeto.ecommerce.services.abstracts;

import com.tobeto.ecommerce.services.dtos.requests.order.AddOrderRequest;
import com.tobeto.ecommerce.services.dtos.responses.order.AddOrderResponse;

public interface OrderService {
    AddOrderResponse add(AddOrderRequest request);
}
