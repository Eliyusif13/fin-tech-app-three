package com.sadiqov.tech_app_three.util;

import com.sadiqov.tech_app_three.dto.request.UserRequestDTO;
import com.sadiqov.tech_app_three.dto.response.CommonResponse;
import com.sadiqov.tech_app_three.dto.response.Status;
import com.sadiqov.tech_app_three.dto.response.StatusCode;
import com.sadiqov.tech_app_three.exception.InvalidDTO;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class DTOUtil {
    @Autowired
    Logger logger;

    public void isValid(UserRequestDTO userRequestDTO) {
        checkDTOInput(userRequestDTO.getName());
        checkDTOInput(userRequestDTO.getSurName());
        checkDTOInput(userRequestDTO.getPassword());
        checkDTOInput(userRequestDTO.getPin());
        checkDTOInput(userRequestDTO.getUserAccountDTOList());
    }

    public <T> void checkDTOInput(T t) {
        if (Objects.isNull(t) || t.toString().isBlank()) {
            logger.error("Invalid input");

            throw InvalidDTO.builder().responseDTO(CommonResponse.builder()
                    .status(Status.builder().
                            statusCode(StatusCode.INVALID_DTO).message("iNVALID Data").build()).
                    build()).build();
        }
    }

}
