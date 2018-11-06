package com.footbalmanager.app.dto.season;

public class PostSeasonRequestDto {
    private String name;
    private String year;
	private Long baseSeasonId;

    public PostSeasonRequestDto() {
    }

	public PostSeasonRequestDto(String name, String year, Long baseSeasonId) {
        this.name = name;
        this.year = year;
		this.baseSeasonId = baseSeasonId;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

	public Long getBaseSeasonId() {
		return baseSeasonId;
	}

}
