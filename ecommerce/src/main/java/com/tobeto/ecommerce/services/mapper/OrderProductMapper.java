package com.tobeto.ecommerce.services.mapper;

import com.tobeto.ecommerce.entities.OrderProduct;
import com.tobeto.ecommerce.services.dtos.requests.order.AddOrderRequest;
import com.tobeto.ecommerce.services.dtos.responses.order.GetAllOrderProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderProductMapper {
    OrderProductMapper INSTANCE = Mappers.getMapper(OrderProductMapper.class);
    @Mapping(target="orderId",source = "order.id")
    @Mapping(target = "productId",source = "product.id")
    @Mapping(target = "userId",source = "order.user.id")
    @Mapping(target = "productName",source =  "product.name")
    @Mapping(target = "userFirstName",source = "order.user.firstName")
    @Mapping(target = "userLastName",source = "order.user.lastName")
    GetAllOrderProductResponse orderProductToGetALlResponse(OrderProduct order);



}
