package com.tobeto.ecommerce.controllers;

import com.tobeto.ecommerce.services.abstracts.OrderService;
import com.tobeto.ecommerce.services.dtos.requests.order.AddOrderRequest;
import com.tobeto.ecommerce.services.dtos.responses.order.AddOrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrdersController {

    private OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public AddOrderResponse add(@RequestBody AddOrderRequest request){
        return orderService.add(request);
    }
}
