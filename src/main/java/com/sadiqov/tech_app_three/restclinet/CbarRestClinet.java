package com.sadiqov.tech_app_three.restclinet;

import com.sadiqov.tech_app_three.dto.response.mbdto.ValCursResponseDTO;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CbarRestClinet {

    @Autowired
    RestTemplate restTemplate;


    public ValCursResponseDTO getCurrency(){
        return null;
    }

}
