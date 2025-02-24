package com.sadiqov.tech_app_three.dto.request;

import com.sadiqov.tech_app_three.util.Currency;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAccountDTO implements Serializable {

    static final long serialVersionUID = 1L;
    BigDecimal balance;
    Currency currency;
    Boolean isActive;
    Integer accountNo;
}
