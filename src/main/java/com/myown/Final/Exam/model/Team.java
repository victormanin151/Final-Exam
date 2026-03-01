package com.myown.Final.Exam.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "manager_full_name")
    private String managerFullName;

    @Column(nullable = false,
            name = "team_group")
    private String teamGroup;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();

    protected Team(){
    }

    public Team(Long id, String name, String managerFullName, String teamGroup) {
        this.id = id;
        this.name = name;
        this.managerFullName = managerFullName;
        this.teamGroup = teamGroup;
    }

    public String getTeamGroup() {
        return teamGroup;
    }

    public String getManagerFullName() {
        return managerFullName;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
