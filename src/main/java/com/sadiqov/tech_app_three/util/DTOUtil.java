package com.sadiqov.tech_app_three.util;

import com.sadiqov.tech_app_three.dto.request.UserRequestDTO;
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

    }

    public <T> void checkDTOInput(T t) {
        if (Objects.isNull(t) || t.toString().isBlank()) {
      logger.error("Invalid input");
        }
    }

}
