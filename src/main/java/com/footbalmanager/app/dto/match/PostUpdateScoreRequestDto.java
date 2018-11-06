package com.footbalmanager.app.dto.match;

public class PostUpdateScoreRequestDto {
    private Long id;
    private int homeScore;
    private int awayScore;

    public PostUpdateScoreRequestDto() {
    }

    public PostUpdateScoreRequestDto(Long id, int home, int away) {
        this.id = id;
        this.homeScore = home;
        this.awayScore = away;
    }

    public Long getId() {
        return id;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }
}
