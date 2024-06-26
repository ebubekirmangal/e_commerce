package com.tobeto.ecommerce.repositories;

import com.tobeto.ecommerce.entities.Product;
import com.tobeto.ecommerce.services.dtos.responses.product.GetAllProductAdminResponse;
import com.tobeto.ecommerce.services.dtos.responses.product.GetAllProductCustomerResponse;
import com.tobeto.ecommerce.services.dtos.responses.product.GetSellerTopFiveResponse;
import com.tobeto.ecommerce.services.dtos.responses.user.GetAllUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    Optional<Product> findByNameIgnoreCase(String name);

    @Query("SELECT new com.tobeto.ecommerce.services.dtos.responses.product.GetAllProductCustomerResponse(p.id, p.name, p.unitPrice, p.category.name) " +
            "FROM Product p " +
            "WHERE (LOWER(:productName) IS NULL OR LOWER(p.name) LIKE CONCAT('%', LOWER(:productName), '%')) " +
            "AND (:minPrice IS NULL OR p.unitPrice >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.unitPrice <= :maxPrice) " +
            "AND (LOWER(:categoryName) IS NULL OR LOWER(p.category.name) = LOWER(:categoryName))")
    List<GetAllProductCustomerResponse> search(String productName, Double minPrice, Double maxPrice, String categoryName);
    @Query("SELECT new com.tobeto.ecommerce.services.dtos.responses.product.GetAllProductAdminResponse(p.id, p.name,p.description,p.unitPrice,p.stockAmount,p.category.name) " +
            "FROM Product p " +
            "WHERE (LOWER(:productName) IS NULL OR LOWER(p.name) LIKE CONCAT('%', LOWER(:productName), '%')) " +
            "AND (LOWER(:categoryName) IS NULL OR LOWER(p.category.name) = LOWER(:categoryName))")
    List<GetAllProductAdminResponse> search(String productName, String categoryName);

    List<Product> findTop5ByOrderByIdDesc();
    @Query("SELECT new com.tobeto.ecommerce.services.dtos.responses.product.GetSellerTopFiveResponse( p.id, p.name, p.description, p.unitPrice, c.name AS categoryName, SUM(op.quantity) AS totalQuantity) " +
            "FROM OrderProduct op " +
            "JOIN op.product p " +
            "JOIN p.category c " +
            "GROUP BY p.id, p.name, p.description, p.unitPrice, c.name " +
            "ORDER BY SUM(op.quantity) DESC")
    List<GetSellerTopFiveResponse> findTop5ProductsByTotalQuantity();

}
