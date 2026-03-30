package com.appdeploy.fastdeploy.image.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SavedImageResponse {

    private String url;

    private String publicId;
}
