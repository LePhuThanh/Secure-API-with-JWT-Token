package com.phuthanh.SpringSecurity_JWT_Token.controllers;

import com.phuthanh.SpringSecurity_JWT_Token.payloads.responses.DataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {
    @GetMapping
    public ResponseEntity<DataResponse> sayHello(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new DataResponse("200","Hello from secured endpoint"));
    }
}
