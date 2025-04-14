package com.sadiqov.tech_app_three.dto.response;

import com.sadiqov.tech_app_three.entity.Account;
import com.sadiqov.tech_app_three.exception.NoActiveAccount;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponseDtoList implements Serializable {

    private static final long serialVersionUID = 1L;

    List<AccountResponseDto> accountResponseDtoList;

    public static AccountResponseDtoList entityDtoAccountList(List<Account> accountList) {
        accountList = accountList.stream().
                filter(Account::getIsActive).
                collect(Collectors.toList());

        if (!accountList.isEmpty()) {
            List<AccountResponseDto> accountResponseList = new ArrayList<>();
            accountList.forEach(account -> accountResponseList.
                    add(AccountResponseDto.
                            entityDto(account)));

            return AccountResponseDtoList.builder().
                    accountResponseDtoList(accountResponseList).build();
        } else {
            throw NoActiveAccount.builder().commonResponse(CommonResponse.builder().
                    status(Status.builder().statusCode(StatusCode.NOT_ACTIVE_ACCOUNT).
                            message("There is no active account").build()).build()).build();

        }
    }
}
