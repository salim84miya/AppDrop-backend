package com.appdeploy.fastdeploy.mapper;

import com.appdeploy.fastdeploy.dto.AppResponseDto;
import com.appdeploy.fastdeploy.entity.App;

public class AppToAppResponseDtoMapper {


    private AppToAppResponseDtoMapper() {
        /* This utility class should not be instantiated */
    }



    public static AppResponseDto mapResponse(App app){

        return AppResponseDto.builder()
                .id(app.getId())
                .appName(app.getAppName())
                .apkLink(app.getApkLink())
                .description(app.getDescription())
                .createdAt(app.getCreatedAt().toString())
                .updatedAt(app.getUpdatedAt().toString())
                .build();
    }

}
