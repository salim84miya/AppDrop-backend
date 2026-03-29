package com.appdeploy.fastdeploy.dto;

import lombok.Data;

@Data
public class AppCreationDto {

    private String appName;

    private String description;

    private String apkLink;
}
