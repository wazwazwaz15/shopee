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
import org.weiga.shopee.annotation.LogExecution;
import org.weiga.shopee.dto.UserCreateRequest;
import org.weiga.shopee.dto.UserLoginRequest;
import org.weiga.shopee.model.ShopeeUser;
import org.weiga.shopee.model.UserInfo;
import org.weiga.shopee.service.JwtService;
import org.weiga.shopee.service.UserService;
import org.weiga.shopee.service.impl.ShopeeUserDetailService;

import java.util.Map;

@RestController
public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final JwtService jwtAuthService;


    private ShopeeUserDetailService shopeeUserDetailService;

    @Autowired
    public UserController(UserService userService, JwtService jwtService, ShopeeUserDetailService shopeeUserDetailService) {
        this.userService = userService;
        this.jwtAuthService = jwtService;
        this.shopeeUserDetailService = shopeeUserDetailService;

    }

    @PostMapping("/member/register")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserCreateRequest userCreateRequest) {

        ShopeeUser shopeeUser = userService.createUser(userCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(shopeeUser);
    }


    @PostMapping("/member/login")
    @LogExecution
    public ResponseEntity<?> loginByUserName(@RequestBody @Valid UserLoginRequest userLoginRequest) {

        UserInfo userInfo = shopeeUserDetailService.loadUserByUsername(userLoginRequest.getEmail());
        log.info("驗證成功");
        log.info("登入者{}", userInfo.getUsername());

        String token = jwtAuthService.auth(userLoginRequest);
        log.info("產生的token:" + token);
        return ResponseEntity.ok(Map.of("token", token, "user", userInfo.getUsername()));

    }


}
