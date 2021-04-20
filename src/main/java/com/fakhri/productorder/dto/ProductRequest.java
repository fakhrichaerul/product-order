package com.fakhri.productorder.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    private String productName;
    private BigDecimal price;
    private Integer quantity;
}
