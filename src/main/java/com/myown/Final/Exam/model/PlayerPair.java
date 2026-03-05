package com.myown.Final.Exam.model;

import java.util.Set;

public record PlayerPair(Set<Long> playerIds) {

    public PlayerPair(Player p1, Player p2) {
        this(Set.of(p1.getId(), p2.getId()));
    }
}
