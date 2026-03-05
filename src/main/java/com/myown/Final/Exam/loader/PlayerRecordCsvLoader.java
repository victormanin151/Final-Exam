package com.myown.Final.Exam.loader;

import com.myown.Final.Exam.dto.PlayerRecordInputDto;
import com.myown.Final.Exam.service.PlayerRecordService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.myown.Final.Exam.loader.MatchCsvLoader.matchesThatWentToPenalties;

@Component
@Order(4)
public class PlayerRecordCsvLoader implements CommandLineRunner {
    private final PlayerRecordService playerRecordService;

    public PlayerRecordCsvLoader (PlayerRecordService playerRecordService) {
        this.playerRecordService = playerRecordService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("PlayerRecordCsvLoader starting...");
        load();
    }
    public void load() throws IOException {
        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("files/records.csv");

        if (inputStream == null) {
            throw new IllegalStateException("records.csv not found");
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8)
        );

        List<PlayerRecordInputDto> dtos = reader.lines()
                .skip(1)
                .map(line -> {
                    String[] cols = line.split("\\s*,\\s*");
                    Long id = Long.parseLong(cols[0].trim());
                    Long playerId = Long.parseLong(cols[1].trim());
                    Long matchId = Long.parseLong(cols[2].trim());
                    Integer fromMinute = Integer.parseInt(cols[3].trim());
                    Integer toMinute;
                    if (cols[4].trim().equalsIgnoreCase("null")) {
                        if(matchesThatWentToPenalties.contains(matchId))
                        {
                            toMinute = 120;
                        }else{
                            toMinute = 90;
                        }
                    } else {
                        toMinute = Integer.parseInt(cols[4].trim());
                    }

                    return new PlayerRecordInputDto(id, playerId, matchId, fromMinute, toMinute);
                })
                .toList();
        dtos.forEach(System.out::println);

        playerRecordService.createPlayerRecord(dtos);

    }
}
