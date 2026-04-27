package com.appdeploy.fastdeploy.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AppUpdateDto {

    @NotBlank
    private Long id;

    private String appName;

    private String description;

    private String apkLink;
}
