package org.weiga.shopee.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserInfo implements UserDetails {
    private final ShopeeUser shopeeUser;

    public UserInfo(ShopeeUser shopeeUser) {
        this.shopeeUser = shopeeUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> {
            return "ROLE_" + shopeeUser.getRole();
        });
    }

    @Override
    public String getPassword() {
        return shopeeUser.getPassword();
    }

    @Override
    public String getUsername() {
        return shopeeUser.getUsername();
    }

    public String getEmail() {
        return shopeeUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
