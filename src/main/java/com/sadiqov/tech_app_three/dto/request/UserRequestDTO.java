package com.sadiqov.tech_app_three.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserRequestDTO implements Serializable {

   private static final long serialVersionUID = 1L;

    String name;
    String surName;
    String password;
    String pin;
    List<AccountRequestDTO> accountRequestDTOList;
}
