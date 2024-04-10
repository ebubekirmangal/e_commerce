package com.tobeto.ecommerce.services.mapper;

import com.tobeto.ecommerce.entities.Product;
import com.tobeto.ecommerce.services.dtos.requests.Product.ProductAddRequest;
import com.tobeto.ecommerce.services.dtos.responses.Product.ProductGetResponse;
import com.tobeto.ecommerce.services.dtos.responses.Product.ProductListingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    @Mapping(target="category.id", source = "categoryId")
    @Mapping(target ="images" ,source = "request.imageId",qualifiedByName = "mapContactIdsToImages")
    Product productFromAddRequest(ProductAddRequest request);
    @Mapping(target = "categoryName",source = "category.name")
    @Mapping(target ="imageUrl" ,source = "images.imageUrl",qualifiedByName = "mapContactIdsToImagesUrl")
    ProductGetResponse toProductGetResponse(Product response);
    @Mapping(target = "categoryName",source = "category.name")
    ProductListingResponse toProductListingResponse(Product response);

    @Named("mapContactIdsToImages")
    default ArrayList<Image> mapContactIdsToImages(List<Integer> imageId) {
        ArrayList<Image> images = new ArrayList<>();
        for (int id : imageId) {
            Image image = new Image();
            image.setId(id);
            images.add(image);
        }
        return images;
    }
    @Named("mapContactIdsToImagesUrl")
    default ArrayList<Image> mapContactIdsToImages(List<String> contactIds) {
        ArrayList<Image> images = new ArrayList<>();
        for (String url : contactIds) {
            Image image = new Image();
            image.setImageUrl(url);
            images.add(image);
        }
        return images;
    }
}
