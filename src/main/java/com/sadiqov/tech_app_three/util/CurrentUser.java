package com.sadiqov.tech_app_three.util;

import com.sadiqov.tech_app_three.dto.response.CommonResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {

    public UserDetails getCurrentUser(){
        return (UserDetails) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
    }
}
