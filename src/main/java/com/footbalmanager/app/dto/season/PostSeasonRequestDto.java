package com.footbalmanager.app.dto.season;

public class PostSeasonRequestDto {
    private String name;
    private String year;

    public PostSeasonRequestDto() {
    }

    public PostSeasonRequestDto(String name, String year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }
}
