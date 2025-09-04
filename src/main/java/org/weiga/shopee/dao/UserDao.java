package org.weiga.shopee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.weiga.shopee.model.ShopeeUser;

import java.util.Optional;
@Repository

public interface UserDao extends JpaRepository<ShopeeUser,Long> {
    ShopeeUser findByUsername(String username);

    ShopeeUser findByEmail(String email);



}
