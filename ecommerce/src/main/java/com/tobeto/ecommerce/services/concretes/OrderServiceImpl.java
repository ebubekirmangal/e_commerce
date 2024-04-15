package com.tobeto.ecommerce.services.concretes;

import com.tobeto.ecommerce.core.utils.exceptions.types.BusinessException;
import com.tobeto.ecommerce.entities.Order;
import com.tobeto.ecommerce.entities.OrderProduct;
import com.tobeto.ecommerce.entities.Product;
import com.tobeto.ecommerce.repositories.OrderProductRepository;
import com.tobeto.ecommerce.repositories.OrderRepository;
import com.tobeto.ecommerce.services.abstracts.OrderService;
import com.tobeto.ecommerce.services.abstracts.ProductService;
import com.tobeto.ecommerce.services.dtos.requests.order.AddOrderRequest;
import com.tobeto.ecommerce.services.dtos.requests.order.GetByIdOrderProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.order.OrderProductRequest;
import com.tobeto.ecommerce.services.dtos.responses.order.GetAllOrderProductResponse;
import com.tobeto.ecommerce.services.dtos.responses.order.AddOrderResponse;
import com.tobeto.ecommerce.services.dtos.responses.order.GetByIdOrderProductResponse;
import com.tobeto.ecommerce.services.mapper.OrderMapper;
import com.tobeto.ecommerce.services.mapper.OrderProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private ProductService productService;

    private OrderRepository orderRepository;

    private OrderProductRepository orderProductRepository;

    @Autowired
    public OrderServiceImpl(ProductService productService, OrderRepository orderRepository,OrderProductRepository orderProductRepository) {
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public AddOrderResponse add(AddOrderRequest request) {
        Order order = OrderMapper.INSTANCE.orderFromAddRequest(request);
        Order savedOrder = orderRepository.save(order);

        List<OrderProductRequest> orderProducts = request.getOrderProductRequest();
        for (OrderProductRequest orderProductRequest : orderProducts) {
            int productId = orderProductRequest.getProductId();
            int quantity = orderProductRequest.getQuantity();
            Double totalPrice = productService.getProductPrice(productId) * quantity;

            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(savedOrder);

            // Ürün bilgilerini al
            Product product = productService.getProductById(productId);
            if (product == null) {
                // Hata işleme veya loglama yapılabilir
                continue;
            }
            orderProduct.setProduct(product);
            orderProduct.setQuantity(quantity);
            orderProduct.setTotalPrice(totalPrice);

            // OrderProduct kaydını veritabanına kaydetme
            orderProductRepository.save(orderProduct);
        }

        AddOrderResponse response = OrderMapper.INSTANCE.OrderToAddResponse(savedOrder);
        return response;
    }

    @Override
    public List<GetAllOrderProductResponse> getAll() {
        List<OrderProduct> orders= orderProductRepository.findAll();
        System.out.println("Veritabanından alınan sipariş sayısı: " + orders.size());
        List<GetAllOrderProductResponse> result = new ArrayList<>();

        for(OrderProduct order:orders){
            GetAllOrderProductResponse dto = OrderProductMapper.INSTANCE.orderProductToGetALlResponse(order);
            result.add(dto);
        }

        return result;
    }
//    public List<GetByIdOrderProductResponse> getById(GetByIdOrderProductRequest request) {
//        List<Order> orders = orderRepository.findById(request.getId())
//                .orElseThrow(()-> new BusinessException("id bulunamadı."));
//       OrderProduct findId =
//       GetByIdOrderProductResponse response = OrderProductMapper.INSTANCE.orderProductToGetByIdResponse(findId);
//        return null;
//    }
}
