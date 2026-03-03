package com.myown.Final.Exam.dto;

import com.myown.Final.Exam.model.Match;
import com.myown.Final.Exam.model.Team;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record MatchInputDto(
        @NotNull(message = "Id cannot be null")
        Long id,

        @NotNull(message = "HomeTeamId cannot be null")
        Long homeTeamId,

        @NotNull(message = "AwayTeamId cannot be null")
        Long awayTeamId,

        @NotNull(message = "Date cannot be null")
        @PastOrPresent(message = "Date cannot be in the future")
        LocalDate date,

        @Min(value = 0, message = "Score must be 0 or greater")
        int homeTeamScore,

        @Min(value = 0, message = "Score must be 0 or greater")
        int awayTeamScore,

        //fix custom annotations later if time
        boolean wentToPenalties,

        @Min(value = 0, message = "Penalty score must be 0 or greater")
        Integer homePenaltyScore,

        @Min(value = 0, message = "Penalty score must be 0 or greater")
        Integer awayPenaltyScore
) {
        public Match toEntity(Team homeTeam, Team awayTeam) {
                return new Match(
                        id,
                        homeTeam,
                        awayTeam,
                        date,
                        homeTeamScore,
                        awayTeamScore,
                        wentToPenalties,
                        homePenaltyScore,
                        awayPenaltyScore
                );
        }
}
