package com.myown.Final.Exam.service;

import com.myown.Final.Exam.dto.PlayerRecordInputDto;
import com.myown.Final.Exam.dto.PlayerRecordOutputDto;
import com.myown.Final.Exam.model.Match;
import com.myown.Final.Exam.model.Player;
import com.myown.Final.Exam.model.PlayerRecord;
import com.myown.Final.Exam.repository.MatchRepository;
import com.myown.Final.Exam.repository.PlayerRecordRepository;
import com.myown.Final.Exam.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerRecordService {

    private final PlayerRecordRepository playerRecordRepository;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;

    public PlayerRecordService(PlayerRecordRepository playerRecordRepository,
                         PlayerRepository playerRepository,
                         MatchRepository matchRepository) {
        this.playerRecordRepository = playerRecordRepository;
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
    }

    @Transactional
    public void createPlayerRecord(PlayerRecordInputDto dto) {

        Player player = playerRepository.findById(dto.playerId())
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));

        Match match = matchRepository.findById(dto.matchId())
                .orElseThrow(() -> new IllegalArgumentException("Match not found"));

        PlayerRecord playerRecord = dto.toEntity(player, match);

        if (dto.toMinute() < dto.fromMinute()) {
            throw new IllegalArgumentException("toMinute cannot be less than fromMinute");
        }

        playerRecordRepository.save(playerRecord);
    }
    @Transactional(readOnly = true)
    public List<PlayerRecordOutputDto> getPlayerRecords(Long playerId) {
        return playerRecordRepository.findByPlayerId(playerId)
                .stream()
                .map(PlayerRecordOutputDto::fromEntity)
                .toList();
    }
}
