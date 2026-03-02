package com.myown.Final.Exam.dto;

import com.myown.Final.Exam.model.Match;
import com.myown.Final.Exam.model.Team;
import jakarta.validation.constraints.NotBlank;
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

        @NotBlank(message = "Score cannot be blank")
        String score
) {
        public Match toEntity(Team homeTeam, Team awayTeam) {
                return new Match(
                        id,
                        homeTeam,
                        awayTeam,
                        date,
                        score
                );
        }
}
