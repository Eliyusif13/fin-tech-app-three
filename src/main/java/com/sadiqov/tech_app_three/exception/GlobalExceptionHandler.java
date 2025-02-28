package com.sadiqov.tech_app_three.exception;

import com.sadiqov.tech_app_three.dto.response.CommonResponse;
import com.sadiqov.tech_app_three.dto.response.Status;
import com.sadiqov.tech_app_three.dto.response.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> internalError(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(CommonResponse.builder()
                .status(Status.builder().statusCode(StatusCode.INTERNAL_ERROR).
                        message("Internal Error").build()).build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

 @ExceptionHandler(value = InvalidDTO.class)
  public ResponseEntity<?> invalidDTO(InvalidDTO invalidDTO) {
      return new ResponseEntity<>(invalidDTO.getResponseDTO(),
              HttpStatus.BAD_REQUEST);
    }
}
