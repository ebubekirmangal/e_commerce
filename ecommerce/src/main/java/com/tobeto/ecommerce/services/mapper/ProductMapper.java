package com.tobeto.ecommerce.services.mapper;

import com.tobeto.ecommerce.entities.Product;
import com.tobeto.ecommerce.services.dtos.requests.product.AddProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.product.UpdateProductRequest;
import com.tobeto.ecommerce.services.dtos.responses.product.AddProductResponse;
import com.tobeto.ecommerce.services.dtos.responses.product.GetByIdProductResponse;
import com.tobeto.ecommerce.services.dtos.responses.product.ListProductResponse;
import com.tobeto.ecommerce.services.dtos.responses.product.UpdateProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    @Mapping(target="category.id", source = "categoryId")
    Product productFromAddRequest(AddProductRequest request);
    @Mapping(target = "categoryName",source = "category.name")
    AddProductResponse toProductAddResponse(Product response);
    @Mapping(target = "categoryName",source = "category.name")
    ListProductResponse toProductListingResponse(Product response);
    @Mapping(target = "categoryName",source = "category.name")
    GetByIdProductResponse toProductGetByIdResponse(Product response);
    @Mapping(target = "category.id",source = "categoryId")
    Product productFromUpdateProductRequest(UpdateProductRequest request);
    @Mapping(target = "categoryName",source = "category.name")
    UpdateProductResponse updateProductResponsetoProduct(Product product);


//    @Named("mapContactIdsToImages")
//    default ArrayList<Image> mapContactIdsToImages(List<Integer> imageId) {
//        ArrayList<Image> images = new ArrayList<>();
//        for (int id : imageId) {
//            Image image = new Image();
//            image.setId(id);
//            images.add(image);
//        }
//        return images;
//    }
//    @Named("mapContactIdsToImagesUrl")
//    default ArrayList<Image> mapContactIdsToImages(List<String> contactIds) {
//        ArrayList<Image> images = new ArrayList<>();
//        for (String url : contactIds) {
//            Image image = new Image();
//            image.setImageUrl(url);
//            images.add(image);
//        }
//        return images;
//    }
}
