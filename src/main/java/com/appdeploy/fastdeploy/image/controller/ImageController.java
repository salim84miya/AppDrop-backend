package com.appdeploy.fastdeploy.image.controller;

import com.appdeploy.fastdeploy.image.dto.ImageModel;
import com.appdeploy.fastdeploy.image.service.ImageService;
import com.appdeploy.fastdeploy.mapper.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/upload/icon")
    public ResponseEntity<?> uploadIcon(@ModelAttribute ImageModel imageModel){

        var response = imageService.saveImage(imageModel);

        return ResponseHandler.builder(response,null, LocalDateTime.now(), HttpStatus.OK);

    }

    @PostMapping("/upload/screenshot/{id}")
    public ResponseEntity<?> uploadScreenshot(
            @PathVariable(value = "id")Long projectId,
            @RequestParam("images")MultipartFile[] files){

        logger.info("files {}",files.length);

        var response = imageService.saveMultipleImages(files,projectId);

        return ResponseHandler.builder(response,null,LocalDateTime.now(),HttpStatus.OK);

    }

    @PutMapping("/update/icon")
    public ResponseEntity<?> updateIcon(@ModelAttribute ImageModel imageModel){

        var response = imageService.updateIcon(imageModel);

        return ResponseHandler.builder(response,null, LocalDateTime.now(), HttpStatus.OK);

    }

    @DeleteMapping("/delete")
    public void deleteFile(UUID id){
        imageService.deleteImage(id);
    }

    @DeleteMapping("/deleteAllImage/{id}")
    public void deleteAllAppImage(@PathVariable(value = "id") Long projectId){
        imageService.allProjectImageDeletion(projectId);
    }

    @GetMapping("/Icon/{id}")
    public ResponseEntity<?> getIcon(@PathVariable(value = "id") Long projectId){

        var response = imageService.getImageIcon(projectId);

        return ResponseHandler.builder(response,null,LocalDateTime.now(),HttpStatus.OK);

    }

    @GetMapping("/ScreenShot/{id}")
    public ResponseEntity<?> getScreenshots(@PathVariable(value = "id") Long projectId){

        var response = imageService.getImageScreenshots(projectId);

        return ResponseHandler.builder(response,null,LocalDateTime.now(),HttpStatus.OK);

    }
}
