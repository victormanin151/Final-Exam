package com.myown.Final.Exam.dto;

import com.myown.Final.Exam.model.Team;
import jakarta.validation.constraints.*;


public record TeamDto(

        @NotNull(message = "Id cannot be null")
        Long id,

        @NotBlank(message = "Team name cannot be empty")
        String teamName,

        @NotBlank(message = "Manager name cannot be empty")
        String managerName,

        @NotBlank(message = "Group cannot be empty")
        @Size(min = 1, max = 1 ,message = "Must be 1 letter")
        String group
) {
        public static TeamDto fromEntity(Team team) {
                return new TeamDto(
                        team.getId(),
                        team.getName(),
                        team.getManagerFullName(),
                        team.getTeamGroup()
                );
        }

        public Team toEntity() {
                return new Team(id, teamName, managerName, group);
        }
}
