package com.sadiqov.tech_app_three.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;
    String tokenForUser;
}
