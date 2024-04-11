package com.tobeto.ecommerce.services.mapper;

import com.tobeto.ecommerce.entities.Order;
import com.tobeto.ecommerce.entities.OrderProduct;
import com.tobeto.ecommerce.services.dtos.requests.order.AddOrderRequest;
import com.tobeto.ecommerce.services.dtos.requests.order.OrderProductRequest;
import com.tobeto.ecommerce.services.dtos.responses.order.AddOrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "user.id",source = "userId")
    Order orderFromAddRequest(AddOrderRequest request);
    @Mapping(target = "orderId",source = "id")
    AddOrderResponse OrderToAddResponse(Order order);
    List<OrderProduct> mapToOrderProducts(List<OrderProductRequest> orderProductRequests);
}
