package com.appdeploy.fastdeploy.image.service;

import com.appdeploy.fastdeploy.image.dto.SavedImageResponse;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {


    private final Cloudinary cloudinary;
    private Logger log = LoggerFactory.getLogger(CloudinaryService.class);

    public SavedImageResponse uploadFile(String folderName, MultipartFile file){


        try{

            Map<Object, Object> options = new HashMap<>();

            options.put("folder", folderName);


            Map uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);

            String publicId = (String) uploadedFile.get("public_id");

            String savedImageUrl = cloudinary.url().secure(true).generate(publicId);

            return new SavedImageResponse(savedImageUrl,publicId);

        } catch (IOException e) {
            log.error("image upload failed {}",e.getLocalizedMessage());
            return null;
        }

    }


    public void deleteImage(String publicId) {

        try{
            cloudinary.uploader().destroy(publicId,Map.of());
        } catch (IOException e) {
            log.error("image delete failed {}",e.getLocalizedMessage());

        }

    }
}
