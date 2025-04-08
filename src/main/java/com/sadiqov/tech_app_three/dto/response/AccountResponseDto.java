package com.sadiqov.tech_app_three.dto.response;

import com.sadiqov.tech_app_three.entity.Account;
import com.sadiqov.tech_app_three.util.Currency;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    BigDecimal balance;
    Currency currency;
    Boolean isActive;
    Integer accountNo;

    public static AccountResponseDto entityDto(Account account){


        return AccountResponseDto.builder().balance(account.getBalance()).
                currency(account.getCurrency())
                .isActive(account.getIsActive())
                .accountNo(account.getAccountNo())
                .build();
    }
}
