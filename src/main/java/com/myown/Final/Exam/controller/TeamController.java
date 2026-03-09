package com.myown.Final.Exam.controller;

import com.myown.Final.Exam.dto.TeamDto;
import com.myown.Final.Exam.service.TeamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<TeamDto> getAllTeams(){
        return teamService.getAllTeams();
    }

}
