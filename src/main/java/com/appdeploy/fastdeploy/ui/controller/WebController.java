package com.appdeploy.fastdeploy.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class WebController {

    @GetMapping("/home")
    public String createPage(Model model){
        model.addAttribute("appName", "My App");
        model.addAttribute("appDescription", "Best app ever");
//        model.addAttribute("appIcon", "url");
        model.addAttribute("apkLink", "url");
//        model.addAttribute("heroImage", "url");
        model.addAttribute("developerName", "Salim");
//        model.addAttribute("screenshots", List.of(...));

        model.addAttribute("features", List.of(
                Map.of("title", "Fast", "description", "Super fast performance"),
                Map.of("title", "Secure", "description", "Your data is safe")
        ));
        return "home";

    }
}
