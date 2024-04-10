package com.tobeto.ecommerce.services.mapper;

import com.tobeto.ecommerce.entities.Product;
import com.tobeto.ecommerce.services.dtos.requests.Product.AddProductRequest;
import com.tobeto.ecommerce.services.dtos.responses.Product.AddProductResponse;
import com.tobeto.ecommerce.services.dtos.responses.Product.GetByIdProductResponse;
import com.tobeto.ecommerce.services.dtos.responses.Product.ListProductResponse;
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
