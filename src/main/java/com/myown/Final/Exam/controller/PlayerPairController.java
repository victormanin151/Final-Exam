package com.myown.Final.Exam.controller;

import com.myown.Final.Exam.dto.PairMinutesDto;
import com.myown.Final.Exam.model.PlayerPair;
import com.myown.Final.Exam.service.PlayerPairService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
    @GetMapping("")
    public List<PairMinutesDto> getPairs(
            @RequestParam(defaultValue = "same") String teamType,
            @RequestParam(defaultValue = "longest") String sort,
            @RequestParam(name="limit",required = false) Integer limit) {

        boolean descending = sort.equalsIgnoreCase("longest");
        playerPairService.calculatePairs();

        Map<PlayerPair, Integer> theMap;

        if ("different".equalsIgnoreCase(teamType)) {
            theMap = playerPairService.getDifferentTeamMinutes();
        } else {
            theMap = playerPairService.getSameTeamMinutes();
        }

        return playerPairService.getPairs(theMap, limit, descending);
    }
}
//    @GetMapping("/different-team/longest")
//    public List<PairMinutesDto> getLongestDifferentTeamPair(@RequestParam (name="limit", required = false) Integer limit) {
//        playerPairService.calculatePairs();
//        return playerPairService.getLongestDifferentTeamPair(limit);
//    }
//}
