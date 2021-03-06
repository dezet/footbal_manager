package com.footbalmanager.app.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.footbalmanager.app.util.converter.LocalDateTimeAttributeConverter;
import org.springframework.data.convert.Jsr310Converters;

import javax.persistence.*;
import java.time.LocalDateTime;

import static org.springframework.util.Assert.notNull;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "date_created", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "home_score", nullable = false)
    private Integer homeScore;

    @Column(name = "away_score", nullable = false)
    private Integer awayScore;

    @Column(name = "played", nullable = false)
    private Boolean played = false;

    @ManyToOne
    @JoinColumn(name = "league_id", nullable = false)
    private League league;

    @ManyToOne
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team home;

    @ManyToOne
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team away;

    Match() {
    }

    public Match(Team home, Team away, LocalDateTime date, Integer homeScore, Integer awayScore, Boolean played, League league) {
//        notNull(home, "Please provide home team");
//        notNull(away, "Please provide away team");
//        notNull(date, "Please provide date");
//        notNull(homeScore, "Please provide home score");
//        notNull(awayScore, "Please provide away score");
//        notNull(played, "Please if match was played");
//        notNull(league, "Please provide league");
        this.home = home;
        this.away = away;
        this.date = date;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.played = played;
        this.league = league;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getHome() {
        return home;
    }

    public void setHome(Team home) {
        this.home = home;
    }

    public Team getAway() {
        return away;
    }

    public void setAway(Team away) {
        this.away = away;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    public Integer getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(Integer awayScore) {
        this.awayScore = awayScore;
    }

    public Boolean getPlayed() {
        return played;
    }

    public void setPlayed(Boolean played) {
        this.played = played;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }
}
