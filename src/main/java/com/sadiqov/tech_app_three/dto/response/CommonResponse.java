package com.sadiqov.tech_app_three.dto.response;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommonResponse<T> implements Serializable {
    static final long serialVersionUID=1L;
}
