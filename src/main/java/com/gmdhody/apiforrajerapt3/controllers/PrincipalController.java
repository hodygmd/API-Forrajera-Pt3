package com.gmdhody.apiforrajerapt3.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class PrincipalController {
    @GetMapping
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World/API-Pt3", HttpStatus.OK);
    }
}
