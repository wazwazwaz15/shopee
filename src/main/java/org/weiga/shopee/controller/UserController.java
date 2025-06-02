package org.weiga.shopee.controller;


import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.weiga.shopee.dto.UserCreateRequest;
import org.weiga.shopee.dto.UserLoginRequest;
import org.weiga.shopee.model.ShopeeUser;
import org.weiga.shopee.service.JwtService;
import org.weiga.shopee.service.UserService;

import java.util.Map;

@RestController
public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtAuthService;
    @PostMapping("/member/register")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserCreateRequest userCreateRequest) {

        ShopeeUser shopeeUser = userService.createUser(userCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(shopeeUser);
    }


    @PostMapping("/member/login")
    public ResponseEntity<?> loginByUserName(@RequestBody @Valid UserLoginRequest userLoginRequest){

        String token = jwtAuthService.auth(userLoginRequest);
        log.info("驗證成功");
        log.info("登入者{}", userLoginRequest.getEmail());
        log.info("產生的token:" + token);
        return ResponseEntity.ok(Map.of("token", token, "user", userLoginRequest.getEmail()));




    }






}
