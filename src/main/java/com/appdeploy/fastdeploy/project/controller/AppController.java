package com.appdeploy.fastdeploy.project.controller;

import com.appdeploy.fastdeploy.project.dto.AppCreationDto;
import com.appdeploy.fastdeploy.project.dto.AppUpdateDto;
import com.appdeploy.fastdeploy.mapper.ResponseHandler;
import com.appdeploy.fastdeploy.project.service.AppService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class AppController {

    private final AppService appService;

    public AppController(AppService appService){
        this.appService = appService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProject(@RequestBody @Valid AppCreationDto appCreationDto){


         var response = appService.createApp(appCreationDto);

         return ResponseHandler.builder(response,null, LocalDateTime.now(), HttpStatus.OK);

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProject(@RequestBody AppUpdateDto appUpdateDto){

        var response = appService.updateApp(appUpdateDto);

        return ResponseHandler.builder(response,null, LocalDateTime.now(), HttpStatus.OK);
    }



}
