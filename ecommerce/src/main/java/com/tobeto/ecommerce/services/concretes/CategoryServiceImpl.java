package com.tobeto.ecommerce.services.concretes;

import com.tobeto.ecommerce.core.utils.exceptions.types.BusinessException;
import com.tobeto.ecommerce.entities.Category;
import com.tobeto.ecommerce.repositories.CategoryRepository;
import com.tobeto.ecommerce.services.abstracts.CategoryService;
import com.tobeto.ecommerce.services.dtos.requests.category.AddCategoryRequest;
import com.tobeto.ecommerce.services.dtos.requests.category.DeleteCategoryRequest;
import com.tobeto.ecommerce.services.dtos.requests.category.GetByIdCategoryRequest;
import com.tobeto.ecommerce.services.dtos.requests.category.UpdateCategoryRequest;
import com.tobeto.ecommerce.services.dtos.responses.category.*;
import com.tobeto.ecommerce.services.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public AddCategoryResponse add(AddCategoryRequest request) {
        Category category = CategoryMapper.INSTANCE.categoryFromAddRequest(request);

        // Eğer parentCategoryId 0 ise, en üst düzey kategori olarak kabul edilir
        if (request.getParentCategoryId() != 0) {
            // Parent kategori belirtilmişse, veritabanından bu kategoriyi alıyoruz
            Category parentCategory = categoryRepository.findById(request.getParentCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent category with id " + request.getParentCategoryId() + " not found"));
            category.setParentCategory(parentCategory);
        }

        // Kategoriyi kaydet
        Category savedCategory = categoryRepository.save(category);

        AddCategoryResponse response = CategoryMapper.INSTANCE.toCategoryAddResponse(savedCategory);
        return response;
    }

    @Override
    public UpdateCategoryResponse update(UpdateCategoryRequest request) {
        Category category = CategoryMapper.INSTANCE.categoryFromUpdateRequest(request);
        Category updated = categoryRepository.save(category);

        UpdateCategoryResponse response = CategoryMapper.INSTANCE.updateToCategoryDto(updated);

        return response;
    }

    @Override
    public List<ListCategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();

        List<ListCategoryResponse> responses = CategoryMapper.INSTANCE.listToCategoryDto(categories);

        return responses;
    }

    @Override
    public GetByIdCategoryResponse getById(GetByIdCategoryRequest request) {

        Category findCategory = categoryRepository.findById(request.getId()).orElseThrow(() -> new BusinessException("Verilen id'de Categori bulunmuyor."));

        GetByIdCategoryResponse response = CategoryMapper.INSTANCE.GetByIdToCategoryDto(findCategory);
        return response;
    }

    @Override
    public DeleteCategoryResponse delete(DeleteCategoryRequest request) {

        Category findCategory = categoryRepository.findById(request.getId()).orElseThrow(() -> new BusinessException("Verilen id'de Categori bulunmuyor."));
        categoryRepository.delete(findCategory);
        DeleteCategoryResponse response = CategoryMapper.INSTANCE.DeleteToCategoryDto(findCategory);
        return response;
    }


}
