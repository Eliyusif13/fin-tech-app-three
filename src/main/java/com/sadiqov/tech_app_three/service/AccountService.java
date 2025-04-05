package com.sadiqov.tech_app_three.service;

import com.sadiqov.tech_app_three.dto.response.AccountResponseDtoList;
import com.sadiqov.tech_app_three.dto.response.CommonResponse;
import com.sadiqov.tech_app_three.dto.response.Status;
import com.sadiqov.tech_app_three.dto.response.StatusCode;
import com.sadiqov.tech_app_three.entity.TechUser;
import com.sadiqov.tech_app_three.repository.UserRepository;
import com.sadiqov.tech_app_three.util.CurrentUser;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@FieldDefaults(level= AccessLevel.PRIVATE)
public class AccountService {

    @Autowired
    CurrentUser currentUser;

    @Autowired
    UserRepository userRepository;

    public CommonResponse<?> getAccount(){
        Optional<TechUser> user = userRepository.findBYPin(currentUser.getCurrentUser().getUsername());

        return CommonResponse.builder().
                status(Status.builder().
                        statusCode(StatusCode.SUCCES).
                        message("Account successfully fetched")
                .build()).data(AccountResponseDtoList.
                        entityDtoAccountList(user.get().getAccountList())).build();

    }
}
