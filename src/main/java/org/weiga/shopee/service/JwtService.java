package org.weiga.shopee.service;

import org.weiga.shopee.dto.UserLoginRequest;

public interface JwtService {

    String auth(UserLoginRequest userLoginRequestByUsername);
}
