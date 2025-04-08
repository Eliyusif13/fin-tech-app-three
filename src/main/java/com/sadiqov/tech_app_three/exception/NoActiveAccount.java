package com.sadiqov.tech_app_three.exception;

import com.sadiqov.tech_app_three.dto.response.CommonResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NoActiveAccount extends RuntimeException {

    CommonResponse<?> commonResponse;
}
