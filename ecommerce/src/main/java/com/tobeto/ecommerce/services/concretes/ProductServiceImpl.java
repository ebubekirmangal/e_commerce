package com.tobeto.ecommerce.services.concretes;

import com.tobeto.ecommerce.core.utils.exceptions.types.BusinessException;
import com.tobeto.ecommerce.entities.Product;
import com.tobeto.ecommerce.repositories.ProductRepository;
import com.tobeto.ecommerce.services.abstracts.ProductService;
import com.tobeto.ecommerce.services.dtos.requests.Product.AddProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.DeleteProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.GetByIdProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.UpdateProductRequest;
import com.tobeto.ecommerce.services.dtos.responses.Product.*;
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
    public AddProductResponse add(AddProductRequest newProduct) {
        nameShouldNotBeSameProductName(newProduct.getName());

        Product product = ProductMapper.INSTANCE.productFromAddRequest(newProduct);
        Product savedProduct = productRepository.save(product);

        AddProductResponse getResponse = ProductMapper.INSTANCE.toProductAddResponse(savedProduct);

        return getResponse;
    }

    @Override
    public DeleteProductResponse delete(DeleteProductRequest request) {
        Product product = productRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("id bulunamadı"));
        productRepository.delete(product);
        DeleteProductResponse productDeleteResponse = new DeleteProductResponse(product.getId(), product.getName(), product.getDescription(), product.getUnitPrice(), product.getStockAmount(),product.getCategory().getName());
        return productDeleteResponse;
    }

    @Override
    public UpdateProductResponse update(UpdateProductRequest product) {

        return null;
    }

    @Override
    public List<ListProductResponse> getALl() {
        List<Product> products = productRepository.findAll();

        List<ListProductResponse> result = new ArrayList<>();

        for(Product product:products){
            ListProductResponse dto = ProductMapper.INSTANCE.toProductListingResponse(product);

            result.add(dto);
        }
        return result;
    }

    public GetByIdProductResponse getById(GetByIdProductRequest request){
        Product product = productRepository.getById(request.getId());
        GetByIdProductResponse getResponse = ProductMapper.INSTANCE.toProductGetByIdResponse(product);
        return getResponse;
    }


    private void nameShouldNotBeSameProductName(String productName){
        Optional<Product> productWithSameName = productRepository.findByNameIgnoreCase(productName);
        if(productWithSameName.isPresent()){
             throw new BusinessException("Aynı isimde bir ürün zaten var.");
        }
    }

}
