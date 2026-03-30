package com.appdeploy.fastdeploy.advices;


import com.appdeploy.fastdeploy.project.exceptions.ProjectNotExistException;
import com.appdeploy.fastdeploy.mapper.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ProjectNotExistException.class)
    public ResponseEntity<?> handleProjectDoesNotExistsException(ProjectNotExistException exception){

        var errorMsg = exception.getMessage();

        Map<String,String> errorMap = new HashMap<>();

        errorMap.put("invalid",errorMsg);

        return ResponseHandler.builder(null,errorMap, LocalDateTime.now(), HttpStatus.BAD_REQUEST);


    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException exception){

        Map<String,String> errorList = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(field->
                    errorList.put(field.getField(), field.getDefaultMessage())
                );

        return ResponseHandler.builder(null,errorList,LocalDateTime.now(),HttpStatus.BAD_REQUEST);
    }
}
