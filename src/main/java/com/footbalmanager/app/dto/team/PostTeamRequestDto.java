package com.footbalmanager.app.dto.team;

import com.footbalmanager.app.domain.League;

public class PostTeamRequestDto {
    private String name;
    private League league;

    public PostTeamRequestDto() {

    }

    public PostTeamRequestDto(String name, League league) {
        this.name = name;
        this.league = league;
    }

    public String getName() {
        return name;
    }

    public League getLeague() {
        return league;
    }
}
