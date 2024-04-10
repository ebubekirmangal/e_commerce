package com.tobeto.ecommerce.services.abstracts;

import com.tobeto.ecommerce.services.dtos.requests.Category.CategoryAddRequest;
import com.tobeto.ecommerce.services.dtos.responses.Category.CategoryAddResponse;

public interface CategoryService {

    CategoryAddResponse add(CategoryAddRequest request);
}
