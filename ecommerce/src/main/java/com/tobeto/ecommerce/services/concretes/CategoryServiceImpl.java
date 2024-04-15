package com.tobeto.ecommerce.services.concretes;

import com.tobeto.ecommerce.core.utils.exceptions.types.BusinessException;
import com.tobeto.ecommerce.entities.Category;
import com.tobeto.ecommerce.entities.User;
import com.tobeto.ecommerce.entities.UserType;
import com.tobeto.ecommerce.repositories.CategoryRepository;
import com.tobeto.ecommerce.repositories.UserRepository;
import com.tobeto.ecommerce.services.abstracts.CategoryService;
import com.tobeto.ecommerce.services.dtos.requests.category.AddCategoryRequest;
import com.tobeto.ecommerce.services.dtos.requests.category.DeleteCategoryRequest;
import com.tobeto.ecommerce.services.dtos.requests.category.GetByIdCategoryRequest;
import com.tobeto.ecommerce.services.dtos.requests.category.UpdateCategoryRequest;
import com.tobeto.ecommerce.services.dtos.responses.category.*;
import com.tobeto.ecommerce.services.dtos.responses.user.GetAllUserId;
import com.tobeto.ecommerce.services.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    private UserRepository userRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AddCategoryResponse add(AddCategoryRequest request) {
        categoryNameEnteredMustNotBeSameDb(request.getName());
//        isAdminDoingAction(request.getUserId());
        Category category = CategoryMapper.INSTANCE.categoryFromAddRequest(request);


        if (request.getParentCategoryId() != 0) {

            Category parentCategory = categoryRepository.findById(request.getParentCategoryId())
                    .orElseThrow(() -> new BusinessException("Belirtilen parentCategoryId '" + request.getParentCategoryId() + "' bulunamadı."));
            category.setParentCategory(parentCategory);
        }

        // Kategoriyi kaydet
        Category savedCategory = categoryRepository.save(category);

        AddCategoryResponse response = CategoryMapper.INSTANCE.toCategoryAddResponse(savedCategory);
        return response;
    }

    @Override
    public UpdateCategoryResponse update(UpdateCategoryRequest request) {
        categoryNameEnteredMustNotBeSameDb(request.getName());
//        isAdminDoingAction(request.getUserId());
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

        Category findCategory =  categoryRepository.findById(request.getId()).orElseThrow(()-> new BusinessException("Verilen id'de Categori bulunmuyor."));

        GetByIdCategoryResponse response = CategoryMapper.INSTANCE.GetByIdToCategoryDto(findCategory);
        return response;
    }

    @Override
    public DeleteCategoryResponse delete(DeleteCategoryRequest request) {
//        isAdminDoingAction(request.getUserId());
        Category findCategory =  categoryRepository.findById(request.getId()).orElseThrow(()-> new BusinessException("Verilen id'de Categori bulunmuyor."));
        categoryRepository.delete(findCategory);
        DeleteCategoryResponse response = CategoryMapper.INSTANCE.DeleteToCategoryDto(findCategory);
        return response;
    }

    private void categoryNameEnteredMustNotBeSameDb(String categoryName){
        Optional<Category> categoryNameFilter = categoryRepository.findByNameIgnoreCase(categoryName);
        if(categoryNameFilter.isPresent()){
            new BusinessException("Aynı isim kategori zaten bulunuyor.");
        }
    }
    public void isAdminDoingAction(int userId) {

        List<GetAllUserId>  ids = userRepository.findAllUserIds();

        if (!ids.contains(userId)) {
            throw new BusinessException("Yönetici iznine sahip değilsiniz.");
        }

    }

}
