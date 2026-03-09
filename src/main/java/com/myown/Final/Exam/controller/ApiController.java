package com.myown.Final.Exam.controller;

import com.myown.Final.Exam.dto.ApiInfoDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private static final List<String> ENDPOINTS = List.of("players", "matches", "teams");

    @Value("${spring.application.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @GetMapping
    public ApiInfoDto ApiInfo(){;
        LocalDateTime currentTime = LocalDateTime.now();
        return new ApiInfoDto(appName, appVersion, currentTime, ENDPOINTS);
    }
}
