package org.weiga.shopee.service;

import org.weiga.shopee.dto.UserCreateRequest;
import org.weiga.shopee.model.ShopeeUser;

public interface UserService {
    ShopeeUser createUser(UserCreateRequest userCreateRequest);




}
