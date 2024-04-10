package com.tobeto.ecommerce.services.abstracts;

import com.tobeto.ecommerce.services.dtos.requests.Order.OrderProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.AddProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.DeleteProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.GetByIdProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.UpdateProductRequest;
import com.tobeto.ecommerce.services.dtos.responses.Product.*;

import java.util.List;

public interface ProductService {
    AddProductResponse add(AddProductRequest request);

    DeleteProductResponse delete(DeleteProductRequest request);

    UpdateProductResponse update(UpdateProductRequest product);

    //List<GetAllProductResponse> getALl();
    GetByIdProductResponse getById(GetByIdProductRequest request);
    // Stok Güncellemesi
    void updateStock(List<OrderProductRequest> orderProducts);
    void updateStock(int productId,int quantity);
    //Arama
    List<GetAllProductCustomerResponse>search(String productName, Double minPrice, Double maxPrice, String categoryName);
    List<GetAllProductAdminResponse>search(String productName, String categoryName);
    List<LastAddProductResponse>getLastAdded();
}
