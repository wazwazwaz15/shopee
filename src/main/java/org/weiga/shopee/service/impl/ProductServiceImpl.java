package org.weiga.shopee.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.weiga.shopee.dao.ProductDao;
import org.weiga.shopee.dto.ProductsQueryParams;
import org.weiga.shopee.model.Product;
import org.weiga.shopee.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
private Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAllProducts() {
        List<Product> productList = productDao.findAll();
        if (productList.size() == 0) {
            log.warn("目前沒有商品");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"沒有商品");


        }
        return productList;
    }
}
