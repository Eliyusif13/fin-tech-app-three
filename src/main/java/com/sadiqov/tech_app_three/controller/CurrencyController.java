package com.sadiqov.tech_app_three.controller;

import com.sadiqov.tech_app_three.service.CbarService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CurrencyController {

    @Autowired
    CbarService cbarService;

    @GetMapping("/currency")

    public ResponseEntity<?> getCurrency() {
        return new ResponseEntity<>(cbarService.getCurrency(), HttpStatus.OK);
    }
}
