package com.tobeto.ecommerce.services.mapper;

import com.tobeto.ecommerce.entities.Product;
import com.tobeto.ecommerce.services.dtos.requests.Product.AddProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.DeleteProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.UpdateProductRequest;
import com.tobeto.ecommerce.services.dtos.responses.Product.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    @Mapping(target="category.id", source = "categoryId")
    Product productFromAddRequest(AddProductRequest request);
    @Mapping(target = "categoryName",source = "category.name")
    AddProductResponse ProductToAddResponse(Product response);
    @Mapping(target = "categoryName",source = "category.name")
    GetAllProductCustomerResponse ProductToGetAllCustomerResponse(Product response);
    @Mapping(target = "categoryName",source = "category.name")
    List<GetAllProductAdminResponse> ProductToGetAllAdminResponse(List<Product> response);
    @Mapping(target = "categoryName",source = "category.name")
    LastAddProductResponse ProductToLastAddResponse(Product response);
    @Mapping(target = "categoryName",source = "category.name")
    GetByIdProductResponse ProductToGetByIdResponse(Product response);
    Product productFromUpdateRequest (UpdateProductRequest request);
    UpdateProductResponse ProductToUpdateResponse(Product response);
    Product ProductFromDeleteRequest(DeleteProductRequest request);
    DeleteProductResponse ProductToDeleteResponse(Product response);


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
