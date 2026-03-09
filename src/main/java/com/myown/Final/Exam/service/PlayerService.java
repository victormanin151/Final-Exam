package com.myown.Final.Exam.service;

import com.myown.Final.Exam.dto.PlayerInputDto;
import com.myown.Final.Exam.dto.PlayerOutputDto;
import com.myown.Final.Exam.model.Player;
import com.myown.Final.Exam.model.Team;
import com.myown.Final.Exam.repository.PlayerRepository;
import com.myown.Final.Exam.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public PlayerService(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @Transactional
    public void createPlayers(List<PlayerInputDto> dtos) {

        List<Player> players = dtos.stream()
                .map(dto -> {
                    Team team = teamRepository
                            .findById(dto.teamId())
                            .orElseThrow(() -> new IllegalArgumentException("Team not found"));

                    return dto.toEntity(team);
                })
                .toList();

        playerRepository.saveAll(players);
    }

    @Transactional(readOnly = true)
    public List<PlayerOutputDto> getAllPlayers() {
        return playerRepository.findAll()
                .stream()
                .map(PlayerOutputDto::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public PlayerOutputDto getPlayerById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No player with that ID"));
        return PlayerOutputDto.fromEntity(player);
    }

    @Transactional(readOnly = true)
    public List<PlayerOutputDto> searchPlayersByName(String name) {
        return playerRepository.findByFullNameContainingIgnoreCase(name)
                .stream()
                .map(PlayerOutputDto::fromEntity)
                .toList();
    }

}
