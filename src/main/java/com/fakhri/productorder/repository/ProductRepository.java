package com.fakhri.productorder.repository;

import com.fakhri.productorder.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query(value = "SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :productName, '%'))")
    List<Product> findByName(@Param("productName") String productName);

    @Query(value = "SELECT COALESCE(MAX(price),0) FROM Product")
    BigDecimal getMaxPrice();

//    Product getMaxPriceJson();

    @Query(value = "SELECT SUM(quantity) FROM Product")
    Integer getSumQuantity();
}
