package com.myown.Final.Exam.service;

import com.myown.Final.Exam.dto.MatchInputDto;
import com.myown.Final.Exam.dto.MatchOutputDto;
import com.myown.Final.Exam.model.Match;
import com.myown.Final.Exam.model.Team;
import com.myown.Final.Exam.repository.MatchRepository;
import com.myown.Final.Exam.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatchService {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    public MatchService(TeamRepository teamRepository,
                        MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @Transactional
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

        if (dto.wentToPenalties()) {
            if (dto.homePenaltyScore() == null || dto.awayPenaltyScore() == null) {
                throw new IllegalArgumentException(
                        "Penalty scores must be provided when match went to penalties"
                );
            }
        } else {
            if (dto.homePenaltyScore() != null || dto.awayPenaltyScore() != null) {
                throw new IllegalArgumentException(
                        "Penalty scores must be null when match did not go to penalties"
                );
            }
        }

        Match match = dto.toEntity(home, away);

        matchRepository.save(match);
    }

    @Transactional(readOnly = true)
    public List<MatchOutputDto> getAllMatches() {
        return matchRepository.findAll()
                .stream()
                .map(MatchOutputDto::fromEntity)
                .toList();
    }
}
