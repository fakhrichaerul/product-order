package com.fakhri.productorder.service;

import com.fakhri.productorder.dto.ProductRequest;
import com.fakhri.productorder.dto.ProductResponse;
import com.fakhri.productorder.dto.ReportResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest request);

    ProductResponse update(Integer id, ProductRequest request) throws Exception;

    void delete(Integer id) throws Exception;

    List<ProductResponse> read();

    List<ProductResponse> findByName(String productName) throws Exception;

    BigDecimal getMaxPrice();

//    ProductResponse getMaxPriceJson();

    Integer getSumQuantity();

    ReportResponse readReport();
}