package com.sadiqov.tech_app_three.restclinet;

import com.sadiqov.tech_app_three.config.ApplicationConfig;
import com.sadiqov.tech_app_three.dto.response.CommonResponse;
import com.sadiqov.tech_app_three.dto.response.Status;
import com.sadiqov.tech_app_three.dto.response.StatusCode;
import com.sadiqov.tech_app_three.dto.response.mbdto.ValCursResponseDTO;
import com.sadiqov.tech_app_three.exception.CbarRestException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CbarRestClinet {

    @Autowired
    RestTemplate restTemplate;


    public ValCursResponseDTO getCurrency() {
        ValCursResponseDTO valCursResponseDTO;

        try {
            valCursResponseDTO = restTemplate.getForObject(ApplicationConfig.urlMB, ValCursResponseDTO.class);
        } catch (Exception e) {
            e.printStackTrace();

            throw cbarRestExceptionMessage();
        }

        if (Objects.isNull(valCursResponseDTO)) {
            throw cbarRestExceptionMessage();
        }


        return valCursResponseDTO;
    }

    private CbarRestException cbarRestExceptionMessage() {
        return CbarRestException.builder().
                commonResponse(CommonResponse.builder().
                        status(Status.builder().statusCode(StatusCode.Cbar_Error).
                                message("Error happened while getting response from Cbar ")
                                .build()).build()).build();
    }
}
