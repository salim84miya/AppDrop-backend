package com.appdeploy.fastdeploy.image.service;

import com.appdeploy.fastdeploy.image.dto.ImageModel;
import com.appdeploy.fastdeploy.image.dto.ImageType;
import com.appdeploy.fastdeploy.image.dto.SavedImageResponse;
import com.appdeploy.fastdeploy.image.entity.Image;
import com.appdeploy.fastdeploy.image.exceptions.FileUploadFailedException;
import com.appdeploy.fastdeploy.image.exceptions.ImageFileMissingException;
import com.appdeploy.fastdeploy.image.exceptions.ImageNameMissingException;
import com.appdeploy.fastdeploy.image.exceptions.ScreenshotCountException;
import com.appdeploy.fastdeploy.image.repository.ImageRepository;
import com.appdeploy.fastdeploy.project.entity.App;
import com.appdeploy.fastdeploy.project.exceptions.ProjectNotExistException;
import com.appdeploy.fastdeploy.project.repository.AppRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final CloudinaryService cloudinaryService;
    private final ImageRepository imageRepository;
    private final AppRepository appRepository;


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
        image.setImageType(imageModel.getImageType());

        SavedImageResponse savedImageData = cloudinaryService.uploadFile(
                "app "+imageModel.getProjectId(),
                imageModel.getFile());

        if(savedImageData==null){
            throw new FileUploadFailedException("File upload to cloudinary failed empty url");
        }

        image.setUrl(savedImageData.getUrl());
        image.setPublicId(savedImageData.getPublicId());

        //get app and reference it
        App app = appRepository.findById(imageModel.getProjectId()).orElseThrow(()->new ProjectNotExistException("Project not found"));

        image.setApp(app);

        return imageRepository.save(image);
    }

    @Transactional
    public List<Image> saveMultipleImages(MultipartFile[] files,Long projectId){

        Optional<List<Image>> apkImages = imageRepository.findImageByAppId(projectId,ImageType.SCREENSHOT.toString());

        if(apkImages.isPresent() && apkImages.get().size()>=2){
            throw new ScreenshotCountException("maximum 3 screenshots allowed already uploaded");
        }

        if(files.length>3){
            throw new ScreenshotCountException("maximum 3 screenshots are allowed");
        }

        if(apkImages.get().size()+files.length>3){
            throw new ScreenshotCountException("maximum 3 screenshots are allowed");
        }

        List<Image> projectImages = new ArrayList<>();

        Arrays.stream(files).forEach(file->
                projectImages.add(
                        saveImage(
                                new ImageModel(
                                        projectId,
                                        file.getName(),
                                        file,ImageType.SCREENSHOT)))
                );

        return projectImages;

    }

    @Transactional
    public Image updateIcon(ImageModel imageModel){

        //find image
        Image image = imageRepository.findIconByApp(imageModel.getProjectId(),imageModel.getImageType().toString())
                .orElseThrow(()->new IllegalArgumentException("No image found"));

        //saved new file
        SavedImageResponse savedImageData = cloudinaryService.uploadFile(
                "app "+imageModel.getProjectId(),
                imageModel.getFile());

        if(savedImageData==null){
            throw new FileUploadFailedException("File upload to cloudinary failed empty url");
        }

        //delete old image
        cloudinaryService.deleteImage(image.getPublicId());

        //update image with new file data
        image.setName(imageModel.getName());
        image.setUrl(savedImageData.getUrl());
        image.setPublicId(savedImageData.getPublicId());

        //get app and reference it
        App app = appRepository.findById(imageModel.getProjectId()).orElseThrow(()->new ProjectNotExistException("Project not found"));

        image.setApp(app);

        return imageRepository.save(image);

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

        List<Image> apkImages = imageRepository.findImageByAppId(projectId,ImageType.SCREENSHOT.toString())
                .orElseThrow(()->new IllegalArgumentException("No images found"));

        apkImages.forEach(image -> {

            cloudinaryService.deleteImage(image.getPublicId());

            imageRepository.delete(image);

        });

    }

    public Image getImageIcon(Long projectId){

        //find image
        return imageRepository.findIconByApp(projectId,ImageType.ICON.toString())
                .orElseThrow(()->new IllegalArgumentException("No image found"));

    }

    public List<Image> getImageScreenshots(Long projectId){

        return  imageRepository.findImageByAppId(projectId,ImageType.SCREENSHOT.toString())
                .orElseThrow(()->new IllegalArgumentException("No images found"));
    }

}
