package com.myown.Final.Exam.controller;


import com.myown.Final.Exam.dto.PlayerOutputDto;
import com.myown.Final.Exam.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping()
    public List<PlayerOutputDto> getAllPlayers(){
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public PlayerOutputDto getPlayerById(@PathVariable Long id){
        return playerService.getPlayerById(id);
    }

    @GetMapping("/search")
    public List<PlayerOutputDto> searchPlayers(@RequestParam String name) {
        return playerService.searchPlayersByName(name);
    }

}
