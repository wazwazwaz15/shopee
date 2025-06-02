package org.weiga.shopee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.weiga.shopee.model.ShopeeUser;

import java.util.Optional;


public interface UserDao extends JpaRepository<ShopeeUser,Long> {
    Optional<ShopeeUser> findByUsername(String username);

    Optional<ShopeeUser> findByEmail(String email);



}
