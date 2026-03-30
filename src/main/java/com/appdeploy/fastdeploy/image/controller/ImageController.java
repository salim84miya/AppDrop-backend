package com.appdeploy.fastdeploy.image.controller;

import com.appdeploy.fastdeploy.image.dto.ImageModel;
import com.appdeploy.fastdeploy.image.service.ImageService;
import com.appdeploy.fastdeploy.mapper.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile( ImageModel imageModel){

        var response = imageService.saveImage(imageModel);

        return ResponseHandler.builder(response,null, LocalDateTime.now(), HttpStatus.OK);

    }

    @DeleteMapping("/delete")
    public void deleteFile(UUID id){
        imageService.deleteImage(id);
    }
}
