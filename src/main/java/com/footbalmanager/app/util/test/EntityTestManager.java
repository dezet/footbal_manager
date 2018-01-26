package com.footbalmanager.app.util.test;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.footbalmanager.app.domain.League;
import com.footbalmanager.app.domain.Match;
import com.footbalmanager.app.domain.Player;
import com.footbalmanager.app.domain.Season;
import com.footbalmanager.app.domain.Team;

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

	public Player getPlayer(String firstname, String lastname, String username, String password, String email) {
		return new Player(firstname, lastname, username, password, email);
	}

    public Match getMatch(Team t1, Team t2) {
        return new Match(t1, t2, LocalDateTime.now(), 0, 0, true, getLeague());
    }

}
