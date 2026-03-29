package com.appdeploy.fastdeploy.advices;


import com.appdeploy.fastdeploy.exceptions.ProjectNotExistException;
import com.appdeploy.fastdeploy.mapper.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ProjectNotExistException.class)
    public ResponseEntity<?> handleProjectDoesNotExistsException(ProjectNotExistException exception){

        var errorMsg = exception.getMessage();

        return ResponseHandler.builder(null,errorMsg, LocalDateTime.now(), HttpStatus.BAD_REQUEST);


    }
}
