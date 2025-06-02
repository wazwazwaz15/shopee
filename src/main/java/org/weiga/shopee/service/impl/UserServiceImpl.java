package org.weiga.shopee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.weiga.shopee.dao.UserDao;
import org.weiga.shopee.dto.UserCreateRequest;
import org.weiga.shopee.model.ShopeeUser;
import org.weiga.shopee.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public ShopeeUser createUser(UserCreateRequest userCreateRequest) {

        if (userDao.findByEmail(userCreateRequest.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (userDao.findByUsername(userCreateRequest.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        ShopeeUser shopeeUser = new ShopeeUser();
        shopeeUser.setUsername(userCreateRequest.getUsername());
        shopeeUser.setPassword(userCreateRequest.getPassword());
        shopeeUser.setEmail(userCreateRequest.getEmail());
        shopeeUser.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        shopeeUser.setRole(userCreateRequest.getRole()!=null? userCreateRequest.getRole() : "USER");

        return userDao.save(shopeeUser);
    }
}
