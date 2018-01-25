package com.footbalmanager.app.dto.league;

import com.footbalmanager.app.domain.Season;

public class PostLeagueRequestDto {
    private String name;
    private Season season;

    public PostLeagueRequestDto() {

    }

    public PostLeagueRequestDto(String name, Season season) {
        this.name = name;
        this.season = season;
    }

    public String getName() {
        return name;
    }

    public Season getSeason() {
        return season;
    }
}
