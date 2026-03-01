package com.myown.Final.Exam.model;

import jakarta.persistence.*;

@Entity
public class Player {
    @Id
    private Long id;

    @Column(name = "team_number")
    private int teamNumber;

    @Column(length = 2)
    private String position;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    protected Player(){}

    public Player(Long id, int teamNumber, String position, String fullName, Team team) {
        this.id = id;
        this.teamNumber = teamNumber;
        this.position = position;
        this.fullName = fullName;
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public String getPosition() {
        return position;
    }

    public String getFullName() {
        return fullName;
    }

    public Team getTeam() {
        return team;
    }
}
