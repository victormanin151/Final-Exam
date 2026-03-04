package com.myown.Final.Exam.service;

import com.myown.Final.Exam.dto.TeamDto;
import com.myown.Final.Exam.model.Team;
import com.myown.Final.Exam.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    public void createTeams(List<TeamDto> dtos) {
        List<Team> teams = dtos.stream()
                .map(TeamDto::toEntity)
                .toList();

        teamRepository.saveAll(teams);
    }

    @Transactional(readOnly = true)
    public List<TeamDto> getAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(TeamDto::fromEntity)
                .toList();
    }
}
