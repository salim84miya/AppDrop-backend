package com.appdeploy.fastdeploy.image.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageModel {

    private Long projectId;

    private String name;

    private MultipartFile file;

    private ImageType imageType;
}
