package com.appdeploy.fastdeploy.dto;

import lombok.Data;

@Data
public class AppUpdateDto {

    private Long id;

    private String appName;

    private String description;

    private String apkLink;
}
