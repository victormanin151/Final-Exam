package com.myown.Final.Exam.loader;

import com.myown.Final.Exam.dto.PlayerInputDto;
import com.myown.Final.Exam.service.PlayerService;
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
@Order(2)
public class PlayerCsvLoader implements CommandLineRunner {
    private final PlayerService playerService;

    public PlayerCsvLoader(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("PlayerCsvLoader starting...");
        load();
    }

    public void load() throws IOException {
        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("files/players.csv");

        if (inputStream == null) {
            throw new IllegalStateException("players.csv not found");
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8)
        );

        List<PlayerInputDto> dtos = reader.lines()
                .skip(1)
                .map(line -> line.split("\\s*,\\s*"))
                .map(columns -> new PlayerInputDto(
                        Long.parseLong(columns[0].trim()),
                        Integer.parseInt(columns[1].trim()),
                        columns[2].trim(),
                        columns[3].trim(),
                        Long.parseLong(columns[4].trim())
                ))
                .toList();
        dtos.forEach(System.out::println);

        playerService.createPlayers(dtos);
    }
}

