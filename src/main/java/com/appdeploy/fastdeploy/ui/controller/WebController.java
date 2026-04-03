package com.appdeploy.fastdeploy.ui.controller;

import com.appdeploy.fastdeploy.image.entity.Image;
import com.appdeploy.fastdeploy.image.service.ImageService;
import com.appdeploy.fastdeploy.project.dto.AppResponseDto;
import com.appdeploy.fastdeploy.project.service.AppService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {

    private final AppService appService;
    private final ImageService imageService;

    public WebController(AppService appService,ImageService imageService){
        this.appService = appService;
        this.imageService = imageService;
    }

    @GetMapping("/home/{name}")
    public String createPage(@PathVariable(value = "name") String appName,Model model){

        AppResponseDto app = appService.fetchProjectByName(appName);

//        Image icon = imageService.getImageIcon(app.getId());

        List<Image> screenshots = imageService.getImageScreenshots(app.getId());

        List<String> screenshotUrls = new ArrayList<>();

        screenshots.forEach(screenshot->
                screenshotUrls.add(screenshot.getUrl())
                );

        model.addAttribute("appName", app.getAppName());
        model.addAttribute("appDescription", app.getDescription());
        model.addAttribute("apkLink", app.getApkLink());
        model.addAttribute("heroImage", screenshots.get(0).getUrl());
        model.addAttribute("screenshots", screenshotUrls);
        model.addAttribute("developerName", "Salim");
        model.addAttribute("githubLink", "https://github.com/...");


//        model.addAttribute("appName", "My App");
//        model.addAttribute("appDescription", "This is my app");
//        model.addAttribute("apkLink", "https://example.com/app.apk");
//        model.addAttribute("heroImage", "https://...");
//        model.addAttribute("screenshots", List.of("url1", "url2"));
//        model.addAttribute("developerName", "Salim");
//        model.addAttribute("githubLink", "https://github.com/...");

        return "home";

    }
}
