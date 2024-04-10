package com.tobeto.ecommerce.services.concretes;

import com.tobeto.ecommerce.core.utils.exceptions.types.BusinessException;
import com.tobeto.ecommerce.entities.Product;
import com.tobeto.ecommerce.repositories.ProductRepository;
import com.tobeto.ecommerce.services.abstracts.ProductService;
import com.tobeto.ecommerce.services.dtos.requests.Order.OrderProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.AddProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.DeleteProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.GetByIdProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.UpdateProductRequest;
import com.tobeto.ecommerce.services.dtos.responses.Product.*;
import com.tobeto.ecommerce.services.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Override
    public AddProductResponse add(AddProductRequest request) {
        nameShouldNotBeSameProductName(request.getName());

        Product product = ProductMapper.INSTANCE.productFromAddRequest(request);
        Product savedProduct = productRepository.save(product);
        AddProductResponse getResponse = ProductMapper.INSTANCE.ProductToAddResponse(savedProduct);
        return getResponse;
    }

    @Override
    public DeleteProductResponse delete(DeleteProductRequest request) {
        Product product = productRepository.findById(request.getId()).orElseThrow(() -> new BusinessException("id bulunamadı"));
        productRepository.delete(product);
        return null;
    }

    @Override
    public UpdateProductResponse update(UpdateProductRequest product) {
        return null;
    }

    @Override
    public GetByIdProductResponse getById(GetByIdProductRequest request) {
        Product product = productRepository.getById(request.getId());
        GetByIdProductResponse getResponse = ProductMapper.INSTANCE.ProductToGetByIdResponse(product);
        return getResponse;
    }

    //Ürün stoklarının güncellendiği kısım
    public void updateStock(List<OrderProductRequest> orderProducts) {
        for(OrderProductRequest orderProduct:orderProducts) {
            int productId=orderProduct.getProductId();
            int quantity=orderProduct.getQuantity();
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new BusinessException("Ürün bulunamadı: " + productId));

            int updatedStock = product.getStockAmount() - quantity;
            if (updatedStock <= 0) {
                throw new BusinessException("Yetersiz stok: " + product.getName());
            }
            product.setStockAmount(updatedStock);
            productRepository.save(product);
        }
    }
    @Override
    public void updateStock(int productId, int quantity) {
        Product product =productRepository.findById(productId)
                .orElseThrow(()-> new BusinessException("Ürün bulunamadı:"+productId));
        int updatedStock=product.getStockAmount()-quantity;
        if(updatedStock<=0)
            throw new BusinessException("Yetersiz stok:" +product.getName());

        product.setStockAmount(updatedStock);
        productRepository.save(product);
    }
    @Override
    public List<GetAllProductCustomerResponse> search(String productName, Double minPrice, Double maxPrice, String categoryName) {
        return productRepository.search(productName,minPrice,maxPrice,categoryName);
    }
    @Override
    public List<GetAllProductAdminResponse> search(String productName, String categoryName) {
        return productRepository.search(productName,categoryName);
    }
    @Override
    public List<LastAddProductResponse> getLastAdded() {
        List<Product> products = productRepository.findTop5ByOrderByIdDesc();
        List<LastAddProductResponse> result = new ArrayList<>();
        for(Product product:products){
            LastAddProductResponse dto=ProductMapper.INSTANCE.ProductToLastAddResponse(product);
            result.add(dto);
        }
        return result;
    }
    private void nameShouldNotBeSameProductName(String productName){
        Optional<Product> productWithSameName = productRepository.findByNameIgnoreCase(productName);
        if(productWithSameName.isPresent()){
             throw new BusinessException("Aynı isimde bir ürün zaten var.");
        }
    }
}
