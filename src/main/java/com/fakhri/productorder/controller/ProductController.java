package com.fakhri.productorder.controller;

import com.fakhri.productorder.dto.ProductRequest;
import com.fakhri.productorder.dto.ProductResponse;
import com.fakhri.productorder.dto.ReportResponse;
import com.fakhri.productorder.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductResponse create(@RequestBody ProductRequest request){
        return productService.create(request);
    }

    @GetMapping
    public List<ProductResponse> getProducts(){
        return productService.read();
    }

    @PutMapping(value = "/{id}")
    public ProductResponse update(@PathVariable(value = "id") Integer id,
                                  @RequestBody ProductRequest request) throws Exception {
        return productService.update(id, request);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable(value = "id") Integer id) throws Exception {
        productService.delete(id);
        return "Success";
    }

    @GetMapping("/find-by-name")
    public List<ProductResponse> getProductsByNameWithHql(@RequestParam(name = "productName") String productName) throws Exception {
        return productService.findByName(productName);
    }

    @GetMapping("/max-price")
    public BigDecimal getMaxPrice(){
        return productService.getMaxPrice();
    }

    @GetMapping("/report")
    public ReportResponse readReport(){
        return productService.readReport();
    }

    @GetMapping("/sum-quantity")
    public Integer getSumQuantity(){
        return productService.getSumQuantity();
    }
}
