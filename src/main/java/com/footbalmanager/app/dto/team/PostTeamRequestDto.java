package com.footbalmanager.app.dto.team;

public class PostTeamRequestDto {
    private String name;
	private Long leagueId;

    public PostTeamRequestDto() {

    }

	public PostTeamRequestDto(String name, Long leagueId) {
        this.name = name;
		this.leagueId = leagueId;
    }

    public String getName() {
        return name;
    }

	public Long getLeagueId() {
		return leagueId;
    }
}
