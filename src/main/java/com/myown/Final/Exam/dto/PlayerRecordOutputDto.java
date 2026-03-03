package com.myown.Final.Exam.dto;

import com.myown.Final.Exam.model.PlayerRecord;
import java.time.LocalDate;

public record PlayerRecordOutputDto(

        Long id,
        String playerName,
        LocalDate matchDate,
        String homeTeamName,
        String awayTeamName,
        int fromMinute,
        int toMinute
) {

    public static PlayerRecordOutputDto fromEntity(PlayerRecord record) {
        return new PlayerRecordOutputDto(
                record.getId(),
                record.getPlayer().getFullName(),
                record.getMatch().getMatchDate(),
                record.getMatch().getHomeTeam().getName(),
                record.getMatch().getAwayTeam().getName(),
                record.getFromMinute(),
                record.getToMinute()
        );
    }
}