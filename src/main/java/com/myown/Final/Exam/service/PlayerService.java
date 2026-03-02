package com.myown.Final.Exam.service;

import com.myown.Final.Exam.dto.PlayerInputDto;
import com.myown.Final.Exam.model.Player;
import com.myown.Final.Exam.model.Team;
import com.myown.Final.Exam.repository.PlayerRepository;
import com.myown.Final.Exam.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public PlayerService(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    public void createPlayer(PlayerInputDto dto) {

        Team team = teamRepository
                .findById(dto.teamId())
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));

        Player player = dto.toEntity(team);

        playerRepository.save(player);
    }
}
