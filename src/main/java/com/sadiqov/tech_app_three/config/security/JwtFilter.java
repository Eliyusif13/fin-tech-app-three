package com.sadiqov.tech_app_three.config.security;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
/*
 * Bu metod HTTP sorğusundan JWT (JSON Web Token) çıxarır və onun etibarlılığını yoxlayır.
 * Əgər token etibarlıdırsa, istifadəçi məlumatları təhlükəsizlik kontekstinə əlavə olunur.
 * Əks halda, sorğu rədd edilir və müvafiq cavab qaytarılır.
 */
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        SecurityContextHolder.clearContext();

        String token = request.getHeader("token");
        if (Objects.nonNull(token) && !token.isEmpty()) {
            if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                UserDetails user = userDetailsService.loadUserByUsername(jwtUtil.getUserPin(token));
                if (jwtUtil.validateToken(token, user)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(request,response);

    }
}
