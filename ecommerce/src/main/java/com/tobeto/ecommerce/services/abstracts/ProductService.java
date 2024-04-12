package com.tobeto.ecommerce.services.abstracts;

import com.tobeto.ecommerce.entities.Product;
import com.tobeto.ecommerce.services.dtos.requests.order.OrderProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.product.AddProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.product.DeleteProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.product.GetByIdProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.product.UpdateProductRequest;
import com.tobeto.ecommerce.services.dtos.responses.product.*;

import java.util.List;

public interface ProductService {
    AddProductResponse add(AddProductRequest newProduct);

    DeleteProductResponse delete(DeleteProductRequest request);

    UpdateProductResponse update(UpdateProductRequest product);

    GetByIdProductResponse getById(GetByIdProductRequest request);

    void updateStock(List<OrderProductRequest> orderProductRequests);

    void updateStock(int productId,int quantity);

    List<GetAllProductCustomerResponse>search(String productName, Double minPrice, Double maxPrice, String categoryName);
    List<GetAllProductAdminResponse>search(String productName, String categoryName);

    List<GetLastAddedProductResponse>getLastAddedProduct();
    Double getProductPrice(int productId);
    Product getProductById(int productId);
}
