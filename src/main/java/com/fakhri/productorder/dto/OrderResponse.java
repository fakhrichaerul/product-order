package com.fakhri.productorder.dto;

import lombok.Data;

@Data
public class OrderResponse {

    private Integer id;
    private ProductResponse product;
}
