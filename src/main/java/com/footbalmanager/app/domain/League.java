package com.footbalmanager.app.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static org.springframework.util.Assert.notNull;

@Entity
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "league")
    private Collection<Team> teams = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "league")
    private Collection<Match> matches = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    League() {
    }

    public League(String name, Season season) {
        notNull(name, "Please provide name");
        notNull(season, "Please provide season");
        this.name = name;
        this.season = season;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Team> getTeams() {
        return teams;
    }

    public void setTeams(Collection<Team> teams) {
        this.teams = teams;
    }

    public Collection<Match> getMatches() {
        return matches;
    }

    public void setMatches(Collection<Match> matches) {
        this.matches = matches;
    }
}
