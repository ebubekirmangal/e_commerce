package com.tobeto.ecommerce.services.concretes;

import com.tobeto.ecommerce.core.utils.exceptions.types.BusinessException;
import com.tobeto.ecommerce.entities.Category;
import com.tobeto.ecommerce.entities.Product;
import com.tobeto.ecommerce.entities.User;
import com.tobeto.ecommerce.entities.UserType;
import com.tobeto.ecommerce.repositories.CategoryRepository;
import com.tobeto.ecommerce.repositories.ProductRepository;
import com.tobeto.ecommerce.repositories.UserRepository;
import com.tobeto.ecommerce.services.abstracts.ProductService;
import com.tobeto.ecommerce.services.dtos.requests.order.OrderProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.product.AddProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.product.DeleteProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.product.GetByIdProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.product.UpdateProductRequest;
import com.tobeto.ecommerce.services.dtos.responses.product.*;
import com.tobeto.ecommerce.services.dtos.responses.user.GetAllUserId;
import com.tobeto.ecommerce.services.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    private UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public Product getProductById(int productId) {
        return productRepository.findById(productId)
                .orElse(null); // veya exception fırlatılabilir
    }

    @Override
    public List<GetTopSellerProductResponse> topSellerProducts() {
        List<GetSellerTopFiveResponse> topFiveResponses = productRepository.findTop5ProductsByTotalQuantity();
        List<GetTopSellerProductResponse> responses = ProductMapper.INSTANCE.toGetTopSellerProductResponseList(topFiveResponses);
        return responses;
    }


    public Double getProductPrice(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Ürün bulunamadı"));

        return product.getUnitPrice();
    }
    @Override
    public AddProductResponse add(AddProductRequest newProduct) {
        nameShouldNotBeSameProductName(newProduct.getName());
//        isAdminDoingAction(newProduct.getUserId());

        Product product = ProductMapper.INSTANCE.productFromAddRequest(newProduct);


        int categoryId = newProduct.getCategoryId();
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {

            throw new BusinessException("Belirtilen categoryId '" + categoryId + "' bulunamadı.");
        }


        product.setCategory(category);


        Product savedProduct = productRepository.save(product);


        AddProductResponse getResponse = ProductMapper.INSTANCE.toProductAddResponse(savedProduct);
        // ürün stoğu yoksa ürün satışa aktif değil
        if(product.getStockAmount() == 0){
            getResponse.setIsActive(Boolean.valueOf("false"));
            ;}
        getResponse.setIsActive(Boolean.valueOf("true"));

        return getResponse;
    }

    @Override
    public DeleteProductResponse delete(DeleteProductRequest request) {
        isAdminDoingAction(request.getUserId());
        Product product = productRepository.findById(request.getId()).orElseThrow(() -> new BusinessException("id bulunamadı"));
        productRepository.delete(product);
        DeleteProductResponse productDeleteResponse = new DeleteProductResponse(product.getId(), product.getName(), product.getDescription(), product.getUnitPrice(), product.getStockAmount(),product.getCategory().getName());
        return productDeleteResponse;
    }

    @Override
    public UpdateProductResponse update(UpdateProductRequest request) {
//        isAdminDoingAction(request.getUserId());

        Product product = ProductMapper.INSTANCE.productFromUpdateProductRequest(request);

        Product updated = productRepository.save(product);

        UpdateProductResponse response = ProductMapper.INSTANCE.updateProductResponsetoProduct(updated);
        return response;
    }

    public List<GetAllProductCustomerResponse> search(String productName, Double minPrice, Double maxPrice, String categoryName) {
        return productRepository.search(productName,minPrice,maxPrice,categoryName);
    }
    @Override
    public List<GetAllProductAdminResponse> search(String productName, String categoryName) {
        return productRepository.search(productName,categoryName);
    }

    @Override
    public List<GetLastAddedProductResponse> getLastAddedProduct() {
        List<Product> products = productRepository.findTop5ByOrderByIdDesc();
        List<GetLastAddedProductResponse> result = new ArrayList<>();
        for (Product product:products){
            GetLastAddedProductResponse dto = ProductMapper.INSTANCE.toLastAddedProductResponse(product);
            result.add(dto);
        }
        return result;
    }

    public GetByIdProductResponse getById(GetByIdProductRequest request){
        int productId = request.getId();

        Product product = productRepository.findById(productId).orElseThrow(()-> new BusinessException("id bulunamadı."));

        GetByIdProductResponse getResponse = ProductMapper.INSTANCE.toProductGetByIdResponse(product);
        return getResponse;
    }


    private void nameShouldNotBeSameProductName(String productName){
        Optional<Product> productWithSameName = productRepository.findByNameIgnoreCase(productName);
        if(productWithSameName.isPresent()){
             throw new BusinessException("Aynı isimde bir ürün zaten var.");
        }
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
    public void isAdminDoingAction(int userId) {

        List<GetAllUserId>  ids = userRepository.findAllUserIds();

        if (!ids.contains(userId)) {
            throw new BusinessException("Yönetici iznine sahip değilsiniz.");
        }

    }

}
