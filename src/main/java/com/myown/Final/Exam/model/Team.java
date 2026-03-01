package com.myown.Final.Exam.model;

import jakarta.persistence.*;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String managerFullName;

    @Column(nullable = false)
    private String group;

    protected Team(){
    }

    public Team(String name, String managerFullName, String group) {
        this.name = name;
        this.managerFullName = managerFullName;
        this.group = group;
    }

    public String getGroup() {
        return group;
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
