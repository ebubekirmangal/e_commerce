package com.tobeto.ecommerce.repositories;


import com.tobeto.ecommerce.entities.Product;
import com.tobeto.ecommerce.services.dtos.responses.Product.GetAllProductAdminResponse;
import com.tobeto.ecommerce.services.dtos.responses.Product.GetAllProductCustomerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findByNameIgnoreCase(String name);

    @Query("SELECT new com.tobeto.ecommerce.services.dtos.responses.Product.GetAllProductCustomerResponse(p.id, p.name, p.unitPrice, p.category.name) " +
            "FROM Product p " +
            "WHERE (LOWER(:productName) IS NULL OR LOWER(p.name) LIKE CONCAT('%', LOWER(:productName), '%')) " +
            "AND (:minPrice IS NULL OR p.unitPrice >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.unitPrice <= :maxPrice) " +
            "AND (LOWER(:categoryName) IS NULL OR LOWER(p.category.name) = LOWER(:categoryName))")
    List<GetAllProductCustomerResponse> search(String productName, Double minPrice, Double maxPrice, String categoryName);
    @Query("SELECT new com.tobeto.ecommerce.services.dtos.responses.Product.GetAllProductAdminResponse(p.id, p.name,p.description,p.unitPrice,p.stockAmount,p.category.name) " +
            "FROM Product p " +
            "WHERE (LOWER(:productName) IS NULL OR LOWER(p.name) LIKE CONCAT('%', LOWER(:productName), '%')) " +
            "AND (LOWER(:categoryName) IS NULL OR LOWER(p.category.name) = LOWER(:categoryName))")
    List<GetAllProductAdminResponse> search(String productName, String categoryName);
    List<Product> findTop5ByOrderByIdDesc();
}
