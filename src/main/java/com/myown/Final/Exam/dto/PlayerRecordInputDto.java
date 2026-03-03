package com.myown.Final.Exam.dto;

import com.myown.Final.Exam.model.Match;
import com.myown.Final.Exam.model.Player;
import com.myown.Final.Exam.model.PlayerRecord;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PlayerRecordInputDto(
        @NotNull(message = "Id cannot be null")
        Long id,

        @NotNull(message = "PlayerId cannot be null")
        Long playerId,

        @NotNull(message = "MatchId cannot be null")
        Long matchId,

        @NotNull
        @Min(0)
        @Max(value = 120, message = "Has to be between 0 and 120 and not null")
        Integer fromMinute,

        @NotNull
        @Min(0)
        @Max(value = 120, message = "Has to be between 0 and 120 and not null")
        Integer toMinute
) {
    public PlayerRecord toEntity(Player player, Match match) {
        return new PlayerRecord(
                id,
                player,
                match,
                fromMinute,
                toMinute
        );
    }
}
