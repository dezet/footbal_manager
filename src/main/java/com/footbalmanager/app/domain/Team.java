package com.footbalmanager.app.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.util.Assert.notNull;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "league_id", nullable = false)
    private League league;
    @OneToMany(mappedBy = "team")
    private Collection<Player> players = new ArrayList<>();
    @OneToMany(mappedBy = "home")
    private Collection<Match> homeMatches = new ArrayList<>();
    @OneToMany(mappedBy = "away")
    private Collection<Match> awayMatches = new ArrayList<>();

    Team() {
    }

    public Team(String name, League league) {
        notNull(name, "Please provide name");
        notNull(league, "Please provide league");
        this.name = name;
        this.league = league;
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

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public void addPlayer(Player p) {
        this.players.add(p);
    }

    public Collection<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Collection<Player> players) {
        this.players = players;
    }

    public Collection<Match> getHomeMatches() {
        return homeMatches;
    }

    public void setHomeMatches(Collection<Match> homeMatches) {
        this.homeMatches = homeMatches;
    }

    public void addHomeMatch(Match m) {
        this.homeMatches.add(m);
    }

    public Collection<Match> getAwayMatches() {
        return awayMatches;
    }

    public void setAwayMatches(Collection<Match> awayMatches) {
        this.awayMatches = awayMatches;
    }

    public void addAwayMatch(Match m) {
        this.awayMatches.add(m);
    }
}
