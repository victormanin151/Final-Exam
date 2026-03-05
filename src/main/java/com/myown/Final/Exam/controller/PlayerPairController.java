package com.myown.Final.Exam.controller;

import com.myown.Final.Exam.dto.PairMinutesDto;
import com.myown.Final.Exam.service.PlayerPairService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pairs")
public class PlayerPairController {

    private final PlayerPairService playerPairService;

    public PlayerPairController(PlayerPairService playerPairService) {
        this.playerPairService = playerPairService;
    }

    @GetMapping("/same-team/longest")
    public PairMinutesDto getLongestSameTeamPair() {
        playerPairService.calculatePairs();
        return playerPairService.getLongestSameTeamPair();
    }

    @GetMapping("/different-team/longest")
    public PairMinutesDto getLongestDifferentTeamPair() {
        playerPairService.calculatePairs();
        return playerPairService.getLongestDifferentTeamPair();
    }
}
