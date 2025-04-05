package com.sadiqov.tech_app_three.dto.response;

import com.sadiqov.tech_app_three.entity.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponseDtoList implements Serializable {

    private static final long serialVersionUID = 1L;

    List<AccountResponseDto> accountResponseDtoList;

    public static AccountResponseDtoList entityDtoAccountList(List<Account> accountList) {
        List<AccountResponseDto> accountResponseList = new ArrayList<>();
        accountList.forEach(account -> accountResponseList.add(AccountResponseDto.entityDto(account)));

        return AccountResponseDtoList.builder().
                accountResponseDtoList(accountResponseList).build();
    }
}
