package com.tobeto.ecommerce.services.concretes;

import com.tobeto.ecommerce.entities.Order;
import com.tobeto.ecommerce.repositories.OrderRepository;
import com.tobeto.ecommerce.repositories.ProductRepository;
import com.tobeto.ecommerce.services.abstracts.OrderService;
import com.tobeto.ecommerce.services.abstracts.ProductService;
import com.tobeto.ecommerce.services.dtos.requests.order.AddOrderRequest;
import com.tobeto.ecommerce.services.dtos.requests.order.OrderProductRequest;
import com.tobeto.ecommerce.services.dtos.responses.order.AddOrderResponse;
import com.tobeto.ecommerce.services.mapper.OrderMapper;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private ProductService productService;

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(ProductService productService, OrderRepository orderRepository) {
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    @Override
    public AddOrderResponse add(AddOrderRequest request) {

        Order order = OrderMapper.INSTANCE.orderFromAddRequest(request);
        Order saved = orderRepository.save(order);

        List<OrderProductRequest> orderProducts = request.getOrderProductRequest();
        for(OrderProductRequest orderProduct:orderProducts){
            int productId=orderProduct.getProductId();
            int quantity= orderProduct.getQuantity();
            productService.updateStock(productId,quantity);
        }
        AddOrderResponse response = OrderMapper.INSTANCE.OrderToAddResponse(saved);
        return response;
    }
}
