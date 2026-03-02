package com.myown.Final.Exam.service;

import com.myown.Final.Exam.dto.MatchInputDto;
import com.myown.Final.Exam.model.Match;
import com.myown.Final.Exam.model.Team;
import com.myown.Final.Exam.repository.MatchRepository;
import com.myown.Final.Exam.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    public MatchService(TeamRepository teamRepository,
                        MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    public void createMatch(MatchInputDto dto) {

        Team home = teamRepository
                .findById(dto.homeTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Home team not found"));

        Team away = teamRepository
                .findById(dto.awayTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Away team not found"));

        if (dto.homeTeamId().equals(dto.awayTeamId())) {
            throw new IllegalArgumentException("Home and Away team cannot be the same");
        }

        Match match = dto.toEntity(home, away);

        matchRepository.save(match);
    }
}
