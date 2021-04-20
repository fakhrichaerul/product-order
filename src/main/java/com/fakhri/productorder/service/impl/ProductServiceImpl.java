package com.fakhri.productorder.service.impl;

import com.fakhri.productorder.dto.ProductRequest;
import com.fakhri.productorder.dto.ProductResponse;
import com.fakhri.productorder.dto.ReportResponse;
import com.fakhri.productorder.model.Product;
import com.fakhri.productorder.repository.ProductRepository;
import com.fakhri.productorder.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        Product product = buildProductModelFromRequest(request);
        Product savedProduct = productRepository.save(product);
        ProductResponse response = buildProductResponseFromModel(savedProduct);

        return response;
    }

    @Override
    public ProductResponse update(Integer id, ProductRequest request) throws Exception {
        Optional<Product> findProduct = productRepository.findById(id);
        if(findProduct.isEmpty()){
            throw new Exception("id not found");
        }

        findProduct.get().setProductName(request.getProductName());
        findProduct.get().setPrice(request.getPrice());
        findProduct.get().setQuantity(request.getQuantity());
        Product savedProduct = productRepository.save(findProduct.get());
        ProductResponse response = buildProductResponseFromModel(savedProduct);

        return response;
    }

    @Override
    public void delete(Integer id) throws Exception {
        Optional<Product> findProduct = productRepository.findById(id);
        if (findProduct.isEmpty()) {
            throw new Exception("id not found");
        }
        productRepository.delete(findProduct.get());
    }

    @Override
    public List<ProductResponse> read() {
        Iterable<Product> products = productRepository.findAll();
        List<ProductResponse> responseList = new ArrayList<>();

        // Use for loop
        /*for (Product product : products) {
            ProductResponse response = buildProductResponseFromModel(product);
            responseList.add(response);
        }*/

        // Use forEach loop
        products.forEach(product -> {
            ProductResponse response = buildProductResponseFromModel(product);
            responseList.add(response);
        });

        return responseList;

    }

    @Override
    public List<ProductResponse> findByName(String productName) throws Exception {
        List<Product> products = productRepository.findByName(productName);
        if (products.isEmpty()){
            throw new Exception("productName not found");
        }

        List<ProductResponse> responseList = new ArrayList<>();
        products.forEach(product -> {
            ProductResponse response = buildProductResponseFromModel(product);
            responseList.add(response);
        });

        return responseList;
    }

    @Override
    public BigDecimal getMaxPrice() {
        return productRepository.getMaxPrice();
    }

//    @Override
//    public ProductResponse getMaxPriceJson() {
//        ProductResponse response = new ProductResponse();
//        response.setMaxPrice(productRepository.getMaxPrice());
//        return response;
//    }

    @Override
    public Integer getSumQuantity() {
        return productRepository.getSumQuantity();
    }

    @Override
    public ReportResponse readReport() {
        ReportResponse response = new ReportResponse();
        response.setMaxPrice(getMaxPrice());
        return null;
    }

    private ProductResponse buildProductResponseFromModel(Product savedProduct) {
        ProductResponse response = new ProductResponse();
        response.setId(savedProduct.getId());
        response.setProductName(savedProduct.getProductName());
        response.setPrice(savedProduct.getPrice());
        response.setQuantity(savedProduct.getQuantity());
        productRepository.getMaxPrice();
        return response;
    }

    private Product buildProductModelFromRequest(ProductRequest request) {
        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        return product;
    }


}
