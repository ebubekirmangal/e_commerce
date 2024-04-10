package com.tobeto.ecommerce.services.concretes;

import com.tobeto.ecommerce.entities.Category;
import com.tobeto.ecommerce.repositories.CategoryRepository;
import com.tobeto.ecommerce.services.abstracts.CategoryService;
import com.tobeto.ecommerce.services.dtos.requests.Category.CategoryAddRequest;
import com.tobeto.ecommerce.services.dtos.responses.Category.CategoryAddResponse;
import com.tobeto.ecommerce.services.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryAddResponse add(CategoryAddRequest request) {
        Category category = CategoryMapper.INSTANCE.categoryFromAddRequest(request);
        Category savedCategory = categoryRepository.save(category);

        CategoryAddResponse response = CategoryMapper.INSTANCE.toCategoryAddResponse(savedCategory);
        return response;
    }
}
