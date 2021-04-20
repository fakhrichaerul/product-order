package com.fakhri.productorder.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    private Integer productQuantity;
}
