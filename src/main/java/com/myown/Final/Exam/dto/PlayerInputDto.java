package com.myown.Final.Exam.dto;

import com.myown.Final.Exam.model.Player;
import com.myown.Final.Exam.model.Team;
import jakarta.validation.constraints.*;

public record PlayerInputDto(
        @NotNull(message = "Id cannot be null")
        Long id,

        @Min(1)
        @Max(value = 99 , message = "Team number has to be between 1 and 99")
        int teamNumber,

        @NotBlank(message = "Position cannot be blank")
        @Pattern(regexp = "^[A-Z]{2}$", message = "Must contain exactly 2 uppercase letters")
        String position,

        @NotBlank(message = "Name cannot be blank")
        String fullName,

        @NotNull(message = "Team ID cannot be null")
        Long teamId
) {
    public Player toEntity(Team team) {
        return new Player(id, teamNumber, position, fullName, team);
    }
}
