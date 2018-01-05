package com.footbalmanager.app.dto.league;

import java.util.List;

public class PatchLeagueRequestDto {
    private String name;

    private List<Integer> teams;

    public PatchLeagueRequestDto(String name, List<Integer> teams) {
        this.name = name;
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getTeams() {
        return teams;
    }
}
