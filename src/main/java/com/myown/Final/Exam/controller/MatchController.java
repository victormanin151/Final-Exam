package com.myown.Final.Exam.controller;

import com.myown.Final.Exam.dto.MatchOutputDto;
import com.myown.Final.Exam.service.MatchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public List<MatchOutputDto> getAllMatches(){
        return matchService.getAllMatches();
    }

    @GetMapping("/{id}")
    public MatchOutputDto getMatchById(@PathVariable Long id) {
        return matchService.getMatchById(id);
    }

    @GetMapping("/penalties")
    public List<MatchOutputDto> getMatchesThatWentToPenalties() {
        return matchService.getMatchesThatWentToPenalties();
    }
}
