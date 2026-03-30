package com.appdeploy.fastdeploy.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppResponseDto {

    private Long id;

    private String appName;

    private String description;

    private String apkLink;

    private String createdAt;

    private String updatedAt;
}
