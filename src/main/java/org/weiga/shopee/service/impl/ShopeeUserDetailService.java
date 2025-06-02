package org.weiga.shopee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.weiga.shopee.dao.UserDao;
import org.weiga.shopee.model.ShopeeUser;
import org.weiga.shopee.model.UserInfo;

@Service
public class ShopeeUserDetailService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserInfo loadUserByUsername(String info) throws UsernameNotFoundException {
        ShopeeUser shopeeUser = userDao.findByEmail(info).get();
        if (shopeeUser == null) {
            shopeeUser = userDao.findByUsername(info).get();
        }

        if (shopeeUser == null) {
            throw new UsernameNotFoundException("帳戶不存在" + info);
        }

        return new UserInfo(shopeeUser);
    }


}
