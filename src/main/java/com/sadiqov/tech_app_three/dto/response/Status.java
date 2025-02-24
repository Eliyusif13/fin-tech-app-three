package com.sadiqov.tech_app_three.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Status implements Serializable {

    static final long serialVersionUID = 1L;

    StatusCode statusCode;
    String message;
}