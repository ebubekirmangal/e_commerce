package com.tobeto.ecommerce.services.abstracts;

import com.tobeto.ecommerce.services.dtos.requests.Product.AddProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.DeleteProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.GetByIdProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.UpdateProductRequest;
import com.tobeto.ecommerce.services.dtos.responses.Product.*;

import java.util.List;

public interface ProductService {
    AddProductResponse add(AddProductRequest newProduct);

    DeleteProductResponse delete(DeleteProductRequest request);

    UpdateProductResponse update(UpdateProductRequest product);

    List<ListProductResponse> getALl();

    GetByIdProductResponse getById(GetByIdProductRequest request);

}
