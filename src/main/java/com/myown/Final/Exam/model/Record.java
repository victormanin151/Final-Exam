package com.myown.Final.Exam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "records")
public class Record {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @Column(nullable = false)
    private Integer fromMinute;

    @Column(nullable = false)
    private Integer toMinute;

    protected Record(){}

    public Record(Long id, Player player, Match match, Integer fromMinute, Integer toMinute) {
        this.id = id;
        this.player = player;
        this.match = match;
        this.fromMinute = fromMinute;
        this.toMinute = toMinute;
    }

    public Long getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public Match getMatch() {
        return match;
    }

    public Integer getFromMinute() {
        return fromMinute;
    }

    public Integer getToMinute() {
        return toMinute;
    }
}
