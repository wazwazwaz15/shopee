package org.weiga.shopee.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.weiga.shopee.model.Product;
import org.weiga.shopee.service.ProductService;

import java.util.List;

@RestController
@Validated
public class ProductController {
    @Autowired
    private ProductService productService;



    @GetMapping("/home/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> productList = productService.findAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }


}
