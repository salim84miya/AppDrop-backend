package com.appdeploy.fastdeploy.security.controller;

import com.appdeploy.fastdeploy.mapper.ResponseHandler;
import com.appdeploy.fastdeploy.security.dto.UserDto;
import com.appdeploy.fastdeploy.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/signup")
    public void signup(@RequestBody UserDto userDto){

        authService.signup(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto){

        var response = authService.loginUser(userDto);

        return ResponseHandler.builder(response,null, LocalDateTime.now(), HttpStatus.OK);
    }

}
