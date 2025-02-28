package com.sadiqov.tech_app_three.exception;

import com.sadiqov.tech_app_three.dto.response.CommonResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvalidDTO extends RuntimeException {

    CommonResponse<?> responseDTO;
}
