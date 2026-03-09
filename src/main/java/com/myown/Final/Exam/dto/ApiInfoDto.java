package com.myown.Final.Exam.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ApiInfoDto (
        String appName,
        String appVersion,
        LocalDateTime currentTime,
        List<String> apiEndpoints
) {}

