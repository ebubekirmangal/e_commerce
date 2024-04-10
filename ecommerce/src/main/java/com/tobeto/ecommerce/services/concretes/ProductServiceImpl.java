package com.tobeto.ecommerce.services.concretes;

import com.tobeto.ecommerce.core.utils.exceptions.types.BusinessException;
import com.tobeto.ecommerce.entities.Product;
import com.tobeto.ecommerce.repositories.ProductRepository;
import com.tobeto.ecommerce.services.abstracts.ProductService;
import com.tobeto.ecommerce.services.dtos.requests.Product.ProductAddRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.ProductUpdateRequest;
import com.tobeto.ecommerce.services.dtos.responses.Product.ProductGetResponse;
import com.tobeto.ecommerce.services.dtos.responses.Product.ProductListingResponse;
import com.tobeto.ecommerce.services.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductGetResponse add(ProductAddRequest newProduct) {
        nameShouldNotBeSameProductName(newProduct.getName());

        Product product = ProductMapper.INSTANCE.productFromAddRequest(newProduct);
        Product savedProduct = productRepository.save(product);

        ProductGetResponse getResponse = ProductMapper.INSTANCE.toProductGetResponse(savedProduct);

        return getResponse;
    }

    @Override
    public void delete(int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("id bulunamadı"));
        productRepository.delete(product);
    }

    @Override
    public ProductGetResponse update(ProductUpdateRequest product) {

        return null;
    }

    @Override
    public List<ProductListingResponse> getALl() {
        List<Product> products = productRepository.findAll();

        List<ProductListingResponse> result = new ArrayList<>();

        for(Product product:products){
            ProductListingResponse dto = ProductMapper.INSTANCE.toProductListingResponse(product);

            result.add(dto);
        }
        return result;
    }

    public ProductGetResponse getById(int request){
        Product product = productRepository.getById(request);
        ProductGetResponse getResponse = ProductMapper.INSTANCE.toProductGetResponse(product);
        return getResponse;
    }


    private void nameShouldNotBeSameProductName(String productName){
        Optional<Product> productWithSameName = productRepository.findByNameIgnoreCase(productName);
        if(productWithSameName.isPresent()){
             throw new BusinessException("Aynı isimde bir ürün zaten var.");
        }
    }

}
