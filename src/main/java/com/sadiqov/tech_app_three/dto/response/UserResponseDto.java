package com.sadiqov.tech_app_three.dto.response;

import com.sadiqov.tech_app_three.dto.request.AccountRequestDTO;
import com.sadiqov.tech_app_three.dto.request.UserRequestDTO;
import com.sadiqov.tech_app_three.entity.TechUser;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserResponseDto implements Serializable {

    static final long serialVersionUID = 1L;
    Long id;
    String name;
    String surName;
    String password;
    String pin;
    String role;
    List<AccountRequestDTO> userRequestDTOList;


    public static UserResponseDto entityResponse(TechUser user) {
        List<AccountRequestDTO> accountListDto = new ArrayList<>();
        user.getAccountList().forEach(a -> accountListDto.add
                (AccountRequestDTO.builder().
                        accountNo(a.getAccountNo()).
                        balance(a.getBalance()).
                        currency(a.getCurrency()).
                        isActive(a.getIsActive()).build()));
        return UserResponseDto.builder().
                id(user.getId()).
                name(user.getName()).
                surName(user.getSurName()).
                password(user.getPassword()).
                pin(user.getPin()).
                role(user.getRole()).
                userRequestDTOList(accountListDto)
                .build();
    }

}
