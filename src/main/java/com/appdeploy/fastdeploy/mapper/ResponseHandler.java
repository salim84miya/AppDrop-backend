package com.appdeploy.fastdeploy.mapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<?> builder(
            Object data,
            Map<String,String> error,
            LocalDateTime localDateTime,
            HttpStatus httpStatus
    ){

        Map<String,Object> response = new HashMap<>();

        response.put("data",data);
        response.put("error",error);
        response.put("httpStatus",httpStatus);
        response.put("timeStamp",localDateTime);

        return new ResponseEntity<>(response,httpStatus);

    }
}
