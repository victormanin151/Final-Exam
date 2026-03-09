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
    public void createMatches(List<MatchInputDto> dtos) {
        List<Match> matches = dtos.stream().map(dto -> {
            Team homeTeam = teamRepository.findById(dto.homeTeamId())
                    .orElseThrow(() -> new IllegalArgumentException("Home team not found: "));
            Team awayTeam = teamRepository.findById(dto.awayTeamId())
                    .orElseThrow(() -> new IllegalArgumentException("Away team not found: "));
            return dto.toEntity(homeTeam, awayTeam);
        }).toList();

        matchRepository.saveAll(matches);
    }

    @Transactional(readOnly = true)
    public List<MatchOutputDto> getAllMatches() {
        return matchRepository.findAll()
                .stream()
                .map(MatchOutputDto::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public MatchOutputDto getMatchById(Long matchId) {
        return matchRepository.findById(matchId)
                .map(MatchOutputDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("Match not found"));
    }

    @Transactional(readOnly = true)
    public List<MatchOutputDto> getMatchesThatWentToPenalties() {
        return matchRepository.findByWentToPenaltiesTrue()
                .stream()
                .map(MatchOutputDto::fromEntity)
                .toList();
    }
}
