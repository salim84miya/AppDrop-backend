package com.appdeploy.fastdeploy.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AppCreationDto {

    @NotBlank
    @Size(min = 3 ,max = 15,message = "app name should be 3 to 15 character long")
    private String appName;

    @NotBlank
    @Size(min = 15 ,max = 150,message = "app name should be 15 to 150 character long")
    private String description;

    @NotBlank
    @Size(min = 15 ,max = 150,message = "app name should be 15 to 150 character long")
    private String apkLink;
}
