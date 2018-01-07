package com.footbalmanager.app.dto.player;

public class PostPlayerRequestDto {
    private String firstname;
    private String lastname;

    public PostPlayerRequestDto() {
    }

    public PostPlayerRequestDto(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
