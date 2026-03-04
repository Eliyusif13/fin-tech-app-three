package com.sadiqov.tech_app_three.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/debug")
@CrossOrigin(origins = "*")
public class DebugController {

    @GetMapping("/ping")
    public ResponseEntity<?> ping(){
        return ResponseEntity.ok().body("pong");
    }

    @PostMapping("/echo")
    public ResponseEntity<?> echo(@RequestBody(required = false) Object body){
        return ResponseEntity.ok().body(body == null ? "no body" : body);
    }

    @GetMapping("/headers")
    public ResponseEntity<?> headers(@RequestHeader Map<String, String> headers){
        // Return a sanitized view of headers for debugging
        return ResponseEntity.ok().body(headers);
    }
}
