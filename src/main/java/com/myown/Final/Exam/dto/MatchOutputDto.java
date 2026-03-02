package com.myown.Final.Exam.dto;

import java.time.LocalDate;

public record MatchOutputDto(
        Long id,
        String homeTeamName,
        String awayTeamName,
        LocalDate date,
        String score
) {
    public static MatchOutputDto fromEntity(com.myown.Final.Exam.model.Match match) {
        return new MatchOutputDto(
                match.getId(),
                match.getHomeTeam().getName(),
                match.getAwayTeam().getName(),
                match.getMatchDate(),
                match.getScore()
        );
    }
}
