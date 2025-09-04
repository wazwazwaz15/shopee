package org.weiga.shopee.service;

import org.weiga.shopee.dto.ProductsQueryParams;
import org.weiga.shopee.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();


}
