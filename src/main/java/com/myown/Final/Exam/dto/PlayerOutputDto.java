package com.myown.Final.Exam.dto;

import com.myown.Final.Exam.model.Player;

public record PlayerOutputDto(
        Long id,
        int teamNumber,
        String position,
        String fullName,
        String teamName
) {
    public static PlayerOutputDto fromEntity(Player player) {
        return new PlayerOutputDto(
                player.getId(),
                player.getTeamNumber(),
                player.getPosition(),
                player.getFullName(),
                player.getTeam().getName()
        );
    }
}
