package com.myown.Final.Exam.controller;

import com.myown.Final.Exam.dto.TeamDto;
import com.myown.Final.Exam.service.TeamService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public TeamDto getTeamById(@PathVariable Long id){
        return teamService.getTeamById(id);
    }

    @GetMapping("/country")
    public List<TeamDto> getTeamsByCountry(@RequestParam String name){
        return teamService.getTeamsByCountryName(name);
    }

    @GetMapping("/group")
    public List<TeamDto> getTeamsByGroup(@RequestParam String letter){
        return teamService.getTeamsByGroup(letter);
    }

}
