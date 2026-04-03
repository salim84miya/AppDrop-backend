package com.appdeploy.fastdeploy.project.service;

import com.appdeploy.fastdeploy.project.dto.AppCreationDto;
import com.appdeploy.fastdeploy.project.dto.AppResponseDto;
import com.appdeploy.fastdeploy.project.dto.AppUpdateDto;
import com.appdeploy.fastdeploy.project.entity.App;
import com.appdeploy.fastdeploy.project.exceptions.ProjectNotExistException;
import com.appdeploy.fastdeploy.mapper.AppToAppResponseDtoMapper;
import com.appdeploy.fastdeploy.project.repository.AppRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppService {

    private final AppRepository appRepository;

    @Transactional
    public AppResponseDto createApp(AppCreationDto appDto){

      App app = new App();

      app.setAppName(appDto.getAppName());
      app.setDescription(appDto.getDescription());
      app.setApkLink(appDto.getApkLink());

      app = appRepository.save(app);

      return AppToAppResponseDtoMapper.mapResponse(app);

    }

    @Transactional
    public AppResponseDto updateApp(AppUpdateDto appUpdateDto){

        App app = appRepository.findById(appUpdateDto.getId()).orElseThrow(()->
                new ProjectNotExistException("No project found with id"));

        app.setApkLink(appUpdateDto.getApkLink());
        app.setAppName(appUpdateDto.getAppName());
        app.setDescription(appUpdateDto.getDescription());

        app = appRepository.save(app);

        return AppToAppResponseDtoMapper.mapResponse(app);

    }

    @Transactional
    public void deleteApp(Long id){

        App app = appRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("No app found with supplied id !"));

        appRepository.delete(app);
    }

    public AppResponseDto fetchProject(Long id){

        App app = appRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("No app found with supplied id !"));

        return AppToAppResponseDtoMapper.mapResponse(app);
    }

    public AppResponseDto fetchProjectByName(String appName){

        App app = appRepository.findByAppName(appName).orElseThrow(()->
                new IllegalArgumentException("No app found with supplied id !"));

        return AppToAppResponseDtoMapper.mapResponse(app);
    }

}
