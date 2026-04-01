package com.appdeploy.fastdeploy.image.service;

import com.appdeploy.fastdeploy.image.dto.ImageModel;
import com.appdeploy.fastdeploy.image.dto.SavedImageResponse;
import com.appdeploy.fastdeploy.image.entity.Image;
import com.appdeploy.fastdeploy.image.exceptions.FileUploadFailedException;
import com.appdeploy.fastdeploy.image.exceptions.ImageFileMissingException;
import com.appdeploy.fastdeploy.image.exceptions.ImageNameMissingException;
import com.appdeploy.fastdeploy.image.repository.ImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final CloudinaryService cloudinaryService;
    private final ImageRepository imageRepository;


    @Transactional
    public Image saveImage(ImageModel imageModel){

        if(imageModel.getFile()==null ){
            throw new ImageFileMissingException("Image file is null");
        }
        if(imageModel.getName()==null){
            throw new ImageNameMissingException("Image name is null");
        }

        Image image = new Image();

        image.setName(imageModel.getName());

        SavedImageResponse savedImageData = cloudinaryService.uploadFile("folder_1", imageModel.getFile());

        if(savedImageData==null){
            throw new FileUploadFailedException("File upload to cloudinary failed empty url");
        }

        image.setUrl(savedImageData.getUrl());
        image.setPublicId(savedImageData.getPublicId());

        return imageRepository.save(image);
    }

    @Transactional
    public List<Image> saveMultipleImages(MultipartFile[] files){

        List<Image> projectImages = new ArrayList<>();

        Arrays.stream(files).forEach(file->
                projectImages.add(saveImage(new ImageModel(file.getName(),file)))
                );

        return projectImages;

    }

    @Transactional
    public void deleteImage(UUID id){

        Image image = imageRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("No image found"));

        cloudinaryService.deleteImage(image.getPublicId());

        imageRepository.delete(image);

    }

    @Transactional
    public void allProjectImageDeletion(Long projectId){



    }
}
