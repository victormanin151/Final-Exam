package com.myown.Final.Exam.service;

import com.myown.Final.Exam.dto.PairMinutesDto;
import com.myown.Final.Exam.model.PlayerRecord;
import com.myown.Final.Exam.model.PlayerPair;
import com.myown.Final.Exam.repository.PlayerRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PlayerPairService {

    private final PlayerRecordRepository playerRecordRepository;

    private Map<PlayerPair, Integer> sameTeamMinutes = new HashMap<>();
    private Map<PlayerPair, Integer> differentTeamMinutes = new HashMap<>();

    public PlayerPairService(PlayerRecordRepository playerRecordRepository) {
        this.playerRecordRepository = playerRecordRepository;
    }

    @Transactional(readOnly = true)
    public void calculatePairs() {
        List<PlayerRecord> records = playerRecordRepository.findAll();

        if(!sameTeamMinutes.isEmpty()){
            return;
        }

        for (int i = 0; i < records.size(); i++) {
            PlayerRecord r1 = records.get(i);

            for (int j = i + 1; j < records.size(); j++) {
                PlayerRecord r2 = records.get(j);

                if (!r1.getMatch().getId().equals(r2.getMatch().getId())) {
                    continue;
                }

                int overlap = Math.min(r1.getToMinute(), r2.getToMinute())
                        - Math.max(r1.getFromMinute(), r2.getFromMinute());

                if (overlap <= 0) {
                    continue;
                }

                PlayerPair pair = new PlayerPair(r1.getPlayer(), r2.getPlayer());

                if (r1.getPlayer().getTeam().getId()
                        .equals(r2.getPlayer().getTeam().getId())) {

                    sameTeamMinutes.merge(pair, overlap, Integer::sum);

                } else {
                    differentTeamMinutes.merge(pair, overlap, Integer::sum);
                }
            }
        }
    }

//    public PairMinutesDto getLongestSameTeamPair() {
//        var entry = sameTeamMinutes.entrySet()
//                .stream()
//                .max(Map.Entry.comparingByValue())
//                .get();
//
//        return new PairMinutesDto(
//                new ArrayList<>(entry.getKey().playerIds()),
//                entry.getValue()
//        );
//    }

    public List<PairMinutesDto> getLongestSameTeamPair(Integer limit) {
        if(limit == null){
            limit = 1;
        }
        var entry = sameTeamMinutes.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(limit).toList();

        return entry.stream()
                .map(e ->
                        new PairMinutesDto(e.getKey().playerIds().stream().toList(),e.getValue()))
                .toList();
    }

    public PairMinutesDto getLongestDifferentTeamPair() {
        var entry = differentTeamMinutes.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get();

        return new PairMinutesDto(
                new ArrayList<>(entry.getKey().playerIds()),
                entry.getValue()
        );
    }
}