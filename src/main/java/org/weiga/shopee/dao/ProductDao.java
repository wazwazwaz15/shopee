package org.weiga.shopee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.weiga.shopee.model.Product;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product,Long> {
    List<Product> findAll();

}
