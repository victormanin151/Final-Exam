package com.myown.Final.Exam.loader;

import com.myown.Final.Exam.dto.TeamDto;
import com.myown.Final.Exam.service.TeamService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@Order(1)
public class TeamCsvLoader implements CommandLineRunner {

    private final TeamService teamService;

    public TeamCsvLoader(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("TeamCsvLoader starting...");
        load();
    }

    public void load() throws IOException {
        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("files/teams.csv");

        if (inputStream == null) {
            throw new IllegalStateException("teams.csv not found");
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8)
        );

        List<TeamDto> dtos = reader.lines()
                .skip(1)
                .map(line -> line.split("\\s*,\\s*"))
                .map(columns -> new TeamDto(
                        Long.parseLong(columns[0].trim()),
                        columns[1].trim(),
                        columns[2].trim(),
                        columns[3].trim()
                ))
                .toList();
        dtos.forEach(System.out::println);

        teamService.createTeams(dtos);
    }
}
