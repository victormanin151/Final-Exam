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

    @Column(name = "homeTeam_score")
    private int homeTeamScore;

    @Column(name = "awayTeam_score")
    private int awayTeamScore;

    @Column(name = "went_to_penalties")
    private boolean wentToPenalties;

    @Column(name = "home_penalty_score")
    private Integer homePenaltyScore;

    @Column(name = "away_penalty_score")
    private Integer awayPenaltyScore;

    protected Match(){}

    public Match(Long id, Team homeTeam, Team awayTeam, LocalDate matchDate, Integer homeTeamScore, Integer awayTeamScore, boolean wentToPenalties, Integer homePenaltyScore, Integer awayPenaltyScore) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchDate = matchDate;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.wentToPenalties = wentToPenalties;
        this.homePenaltyScore = homePenaltyScore;
        this.awayPenaltyScore = awayPenaltyScore;
    }

    public Long getId() {
        return id;
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

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public boolean wentToPenalties() {
        return wentToPenalties;
    }

    public Integer getHomePenaltyScore() {
        return homePenaltyScore;
    }

    public Integer getAwayPenaltyScore() {
        return awayPenaltyScore;
    }
}
