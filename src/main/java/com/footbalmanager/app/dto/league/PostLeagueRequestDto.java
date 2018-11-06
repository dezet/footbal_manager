package com.footbalmanager.app.dto.league;

public class PostLeagueRequestDto {
    private String name;
	private Long seasonId;

    public PostLeagueRequestDto() {

    }

	public PostLeagueRequestDto(String name, Long seasonId) {
        this.name = name;
		this.seasonId = seasonId;
    }

    public String getName() {
        return name;
    }

	public Long getSeasonId() {
		return seasonId;
    }
}
