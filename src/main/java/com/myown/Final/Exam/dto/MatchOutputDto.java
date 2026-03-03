package com.myown.Final.Exam.dto;

import java.time.LocalDate;

public record MatchOutputDto(
        Long id,
        String homeTeamName,
        String awayTeamName,
        LocalDate date,
        int homeTeamScore,
        int awayTeamScore,
        boolean wentToPenalties,
        Integer homePenaltyScore,
        Integer awayPenaltyScore
) {
    public static MatchOutputDto fromEntity(com.myown.Final.Exam.model.Match match) {
        return new MatchOutputDto(
                match.getId(),
                match.getHomeTeam().getName(),
                match.getAwayTeam().getName(),
                match.getMatchDate(),
                match.getHomeTeamScore(),
                match.getAwayTeamScore(),
                match.wentToPenalties(),
                match.getHomePenaltyScore(),
                match.getAwayPenaltyScore()
        );
    }
}
