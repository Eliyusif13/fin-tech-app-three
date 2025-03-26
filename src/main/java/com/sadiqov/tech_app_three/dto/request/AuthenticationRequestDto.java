package com.sadiqov.tech_app_three.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class AuthenticationRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;
    String password;
    String pin;

}
