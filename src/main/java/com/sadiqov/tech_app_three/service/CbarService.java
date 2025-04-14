package com.sadiqov.tech_app_three.service;

import com.sadiqov.tech_app_three.dto.response.CommonResponse;
import com.sadiqov.tech_app_three.dto.response.Status;
import com.sadiqov.tech_app_three.dto.response.StatusCode;
import com.sadiqov.tech_app_three.dto.response.mbdto.ValCursResponseDTO;
import com.sadiqov.tech_app_three.restclinet.CbarRestClinet;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CbarService {
    @Autowired
    CbarRestClinet cbarRestClinet;

    public CommonResponse<?> getCurrency(){
        ValCursResponseDTO currency = cbarRestClinet.getCurrency();
        return CommonResponse.builder().status(Status.builder().
                statusCode(StatusCode.SUCCESS).message("All Currencies").build()).data(currency).build();
    }
}
