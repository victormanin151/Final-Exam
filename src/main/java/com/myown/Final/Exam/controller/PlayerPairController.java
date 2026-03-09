package com.myown.Final.Exam.controller;

import com.myown.Final.Exam.dto.PairMinutesDto;
import com.myown.Final.Exam.service.PlayerPairService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pairs")
public class PlayerPairController {

    private final PlayerPairService playerPairService;

    public PlayerPairController(PlayerPairService playerPairService) {
        this.playerPairService = playerPairService;
    }

//    @GetMapping("/same-team/longest")
//    public List<PairMinutesDto> getLongestSameTeamPair(@RequestParam (name="limit", required = false) Integer limit) {
//        playerPairService.calculatePairs();
//        return playerPairService.getSameTeamPair(limit, true);
//    }
//
//    @GetMapping("/same-team/least")
//    public List<PairMinutesDto> getLeastSameTeamPair(@RequestParam (name="limit", required = false) Integer limit) {
//        playerPairService.calculatePairs();
//        return playerPairService.getSameTeamPair(limit, false);
//    }
    @GetMapping("/same-team")
    public List<PairMinutesDto> getSameTeamPairs(
            @RequestParam(defaultValue = "longest") String sort,
            @RequestParam(name="limit",required = false) Integer limit) {

        boolean descending = sort.equalsIgnoreCase("longest");
        playerPairService.calculatePairs();
        return playerPairService.getSameTeamPair(limit, descending);
    }

    @GetMapping("/different-team/longest")
    public List<PairMinutesDto> getLongestDifferentTeamPair(@RequestParam (name="limit", required = false) Integer limit) {
        playerPairService.calculatePairs();
        return playerPairService.getLongestDifferentTeamPair(limit);
    }
}
