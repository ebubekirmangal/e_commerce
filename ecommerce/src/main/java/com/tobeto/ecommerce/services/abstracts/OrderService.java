package com.tobeto.ecommerce.services.abstracts;

import com.tobeto.ecommerce.services.dtos.requests.order.AddOrderRequest;
import com.tobeto.ecommerce.services.dtos.responses.order.AddOrderResponse;
import com.tobeto.ecommerce.services.dtos.responses.order.GetAllOrderResponse;

import java.util.List;

public interface OrderService {
    AddOrderResponse add(AddOrderRequest request);
    List<GetAllOrderResponse> getAll();
}
