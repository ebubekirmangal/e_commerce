package com.tobeto.ecommerce.services.concretes;

import com.tobeto.ecommerce.core.utils.exceptions.types.BusinessException;
import com.tobeto.ecommerce.entities.Category;
import com.tobeto.ecommerce.entities.Product;
import com.tobeto.ecommerce.repositories.CategoryRepository;
import com.tobeto.ecommerce.repositories.ProductRepository;
import com.tobeto.ecommerce.services.abstracts.ProductService;
import com.tobeto.ecommerce.services.dtos.requests.order.OrderProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.product.AddProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.product.DeleteProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.product.GetByIdProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.product.UpdateProductRequest;
import com.tobeto.ecommerce.services.dtos.responses.product.*;
import com.tobeto.ecommerce.services.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    public Product getProductById(int productId) {
        return productRepository.findById(productId)
                .orElse(null); // veya exception fırlatılabilir
    }




    public Double getProductPrice(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Ürün bulunamadı"));

        return product.getUnitPrice();
    }
    @Override
    public AddProductResponse add(AddProductRequest newProduct) {
        nameShouldNotBeSameProductName(newProduct.getName());

        // AddProductRequest'ten Product'a dönüştürme
        Product product = ProductMapper.INSTANCE.productFromAddRequest(newProduct);

        // Yeni ürünün kategorisini alın
        int categoryId = newProduct.getCategoryId();
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            // Belirtilen kategori bulunamadı
            throw new BusinessException("Category with id " + categoryId + " not found");
        }

        // Ürüne kategoriyi atama
        product.setCategory(category);

        // Ürünü kaydetme
        Product savedProduct = productRepository.save(product);

        // Kaydedilen ürünü AddProductResponse'ya dönüştürme
        AddProductResponse getResponse = ProductMapper.INSTANCE.toProductAddResponse(savedProduct);

        return getResponse;
    }

    @Override
    public DeleteProductResponse delete(DeleteProductRequest request) {
        Product product = productRepository.findById(request.getId()).orElseThrow(() -> new BusinessException("id bulunamadı"));
        productRepository.delete(product);
        DeleteProductResponse productDeleteResponse = new DeleteProductResponse(product.getId(), product.getName(), product.getDescription(), product.getUnitPrice(), product.getStockAmount(),product.getCategory().getName());
        return productDeleteResponse;
    }

    @Override
    public UpdateProductResponse update(UpdateProductRequest request) {
        Product product = ProductMapper.INSTANCE.productFromUpdateProductRequest(request);

        Product updated = productRepository.save(product);

        UpdateProductResponse response = ProductMapper.INSTANCE.updateProductResponsetoProduct(updated);
        return response;
    }
    @Override
    public List<GetAllTopSellingProductResponse> search(String productName, int salesCount) {
        return productRepository.search(productName,salesCount);
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
        List<GetLastAddedProductResponse> lastAddedProductResponses = new ArrayList<>();
        for (Product product:products){
            GetLastAddedProductResponse dto = ProductMapper.INSTANCE.toLastAddedProductResponse(product);
            lastAddedProductResponses.add(dto);
        }
        return lastAddedProductResponses;
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

}
