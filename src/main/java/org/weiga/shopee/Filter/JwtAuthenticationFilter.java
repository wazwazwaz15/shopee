package org.weiga.shopee.Filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.weiga.shopee.model.UserInfo;
import org.weiga.shopee.service.impl.ShopeeUserDetailService;
import org.weiga.shopee.util.JwtUtil;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String HEADER_AUTH = "Authorization";

    @Autowired
    private ShopeeUserDetailService detailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HEADER_AUTH);
        if (authHeader != null) {
            String accessToken = authHeader.replace("Bearer ", "");
            String username = JwtUtil.parseToken(accessToken);

            UserInfo userInfo = detailService.loadUserByUsername(username);

            Authentication authentication = new UsernamePasswordAuthenticationToken(userInfo.getUsername(), userInfo.getPassword(), userInfo.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);


        }

        filterChain.doFilter(request, response);

    }
}
