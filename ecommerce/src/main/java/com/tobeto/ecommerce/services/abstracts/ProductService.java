package com.tobeto.ecommerce.services.abstracts;

import com.tobeto.ecommerce.services.dtos.requests.Product.ProductAddRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.ProductUpdateRequest;
import com.tobeto.ecommerce.services.dtos.responses.Product.ProductGetResponse;
import com.tobeto.ecommerce.services.dtos.responses.Product.ProductListingResponse;

import java.util.List;

public interface ProductService {
    ProductGetResponse add(ProductAddRequest newProduct);

    void delete(int request);

    ProductGetResponse update(ProductUpdateRequest product);

    List<ProductListingResponse> getALl();

    ProductGetResponse getById(int request);

}
