package com.footbalmanager.app.util.test;

import com.footbalmanager.app.domain.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("entityTestManager")
public class EntityTestManager {

    public Season getSeason() {
        return new Season("name1", "2017");
    }

    public League getLeague() {
        return new League("league1", getSeason());
    }

    public Team getTeam(String name) {
        return new Team(name, getLeague());
    }

    public Player getPlayer(String firstname, String lastname) {
        return new Player(firstname, lastname);
    }

    public Match getMatch(Team t1, Team t2) {
        return new Match(t1, t2, LocalDateTime.now(), 0, 0, true, getLeague());
    }

}
