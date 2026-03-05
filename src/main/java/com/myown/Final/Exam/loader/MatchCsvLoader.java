package com.myown.Final.Exam.loader;

import com.myown.Final.Exam.dto.MatchInputDto;
import com.myown.Final.Exam.service.MatchService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Order(3)
public class MatchCsvLoader implements CommandLineRunner {
    public static final Set<Long> matchesThatWentToPenalties = new HashSet<>();

    private final MatchService matchService;

    public MatchCsvLoader(MatchService matchService){
        this.matchService = matchService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("MatchCsvLoader starting...");
        load();
    }

    public void load() throws IOException {
        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("files/matches.csv");

        if (inputStream == null) {
            throw new IllegalStateException("matches.csv not found");
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8)
        );

        List<MatchInputDto> dtos = reader.lines()
                .skip(1)
                .map(line -> {
                    String[] cols = line.split("\\s*,\\s*");
                    Long id = Long.parseLong(cols[0].trim());
                    Long homeTeamId = Long.parseLong(cols[1].trim());
                    Long awayTeamId = Long.parseLong(cols[2].trim());
                    LocalDate date = LocalDate.parse(cols[3].trim(), DateTimeFormatter.ofPattern("M/d/yyyy"));

                    String scorePart = cols[4].trim();
                    boolean wentToPenalties = scorePart.contains("(");

                    int homeScore;
                    int awayScore;
                    Integer homePenalty = null;
                    Integer awayPenalty = null;

                    String[] sides = scorePart.split("-");
                    if (wentToPenalties) {
                        String homeSide = sides[0];
                        String awaySide = sides[1];

                        homeScore = Integer.parseInt(homeSide.substring(0, homeSide.indexOf('(')));
                        awayScore = Integer.parseInt(awaySide.substring(0, awaySide.indexOf('(')));

                        homePenalty = Integer.parseInt(homeSide.substring(homeSide.indexOf('(') + 1, homeSide.indexOf(')')));
                        awayPenalty = Integer.parseInt(awaySide.substring(awaySide.indexOf('(') + 1, awaySide.indexOf(')')));
                        matchesThatWentToPenalties.add(id);
                    } else {
                        homeScore = Integer.parseInt(sides[0]);
                        awayScore = Integer.parseInt(sides[1]);
                    }

                    return new MatchInputDto(id, homeTeamId, awayTeamId, date, homeScore, awayScore, wentToPenalties, homePenalty, awayPenalty);
                })
                .toList();
        dtos.forEach(System.out::println);

        matchService.createMatches(dtos);
    }
}
