package org.weiga.shopee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.weiga.shopee.dto.UserLoginRequest;
import org.weiga.shopee.service.JwtService;
import org.weiga.shopee.util.JwtUtil;

@Service
public class JwtServiceImpl implements JwtService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String auth(UserLoginRequest userLoginRequest) {
        String email = userLoginRequest.getEmail();
        String password = userLoginRequest.getPassword();
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        authentication = authenticationManager.authenticate(authentication);
        String token = JwtUtil.generateToken(authentication);

        return token;
    }
}
