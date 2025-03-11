package com.sadiqov.tech_app_three.config.security;

import com.sadiqov.tech_app_three.dto.response.CommonResponse;
import com.sadiqov.tech_app_three.dto.response.Status;
import com.sadiqov.tech_app_three.dto.response.StatusCode;
import com.sadiqov.tech_app_three.entity.TechUser;
import com.sadiqov.tech_app_three.exception.NoSuchUserExits;
import com.sadiqov.tech_app_three.repository.UserRepository;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Logger logger;

    @Override
    public UserDetails loadUserByUsername(String pin) throws UsernameNotFoundException {
        Optional<TechUser> byPin = userRepository.findBYPin(pin);
        if (byPin.isPresent()) {
            return new UserDetailsImpl(byPin.get());
        } else {
            logger.error("There is no user with pin : " + pin);
            throw NoSuchUserExits.builder().commonResponse(CommonResponse.builder().
                    status(Status.builder().statusCode(StatusCode.USER_NOT_EXITS).
                            message("There is no user with pin : " + pin)
                            .build()).build()).build();
        }
    }
}
