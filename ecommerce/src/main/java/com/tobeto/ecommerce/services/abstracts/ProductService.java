package com.tobeto.ecommerce.services.abstracts;

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

    List<ListProductResponse> getALl();

    GetByIdProductResponse getById(GetByIdProductRequest request);

}
