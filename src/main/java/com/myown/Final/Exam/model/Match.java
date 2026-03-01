package com.myown.Final.Exam.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Match {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "players"})
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "players"})
    private Team awayTeam;

    @Column(name = "match_date")
    private LocalDate matchDate;

    @Column(name = "match_score")
    private String score;

    protected Match(){}

    public Match(Long id, Team homeTeam, Team awayTeam, LocalDate matchDate, String score) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchDate = matchDate;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public String getScore() {
        return score;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }
}
