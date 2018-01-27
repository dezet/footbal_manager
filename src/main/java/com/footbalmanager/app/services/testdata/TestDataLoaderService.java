package com.footbalmanager.app.services.testdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.footbalmanager.app.domain.League;
import com.footbalmanager.app.domain.Match;
import com.footbalmanager.app.domain.Player;
import com.footbalmanager.app.domain.Season;
import com.footbalmanager.app.domain.Team;
import com.footbalmanager.app.repository.LeagueRepository;
import com.footbalmanager.app.repository.PlayerRepository;
import com.footbalmanager.app.repository.SeasonRepository;
import com.footbalmanager.app.repository.TeamRepository;
import com.footbalmanager.app.services.TimetableService;

@Service
public class TestDataLoaderService {

	@Autowired
	private LeagueRepository leagueRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private TimetableService timetableService;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private SeasonRepository seasonRepository;

	@PostConstruct
	public void init() {
		Player admin = new Player("admin", "admin", "admin", "admin", "admin");
		admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
		playerRepository.save(admin);
		List<Season> seasons = createTestSeasons();
		List<League> leagues = createTestLeagues(seasons);
		List<Team> teams = createTestTeams(leagues);
		createTestPlayers(teams);
		createTestMatches(seasons);
	}

	private List<Player> createTestPlayers(List<Team> teams) {
		List<Player> players = new ArrayList<>();
		teams.forEach(team -> {
			for (int i = 0; i < 12; i++) {
				Player player = new Player("firstName" + i + team.getName(), "lastName" + i + team.getName(),
						"userName" + i + team.getName(), "passwd" + i + team.getName(),
						i + team.getName() + "@mail.com");
				player.setTeam(team);
				players.add(playerRepository.save(player));
			}
		});
		return players;
	}

	private List<Team> createTestTeams(List<League> leagues) {
		List<Team> teams = new ArrayList<>();
		leagues.forEach(league -> {
			for (int i = 0; i < 11; i++) {
				Team team = new Team("team" + i + league.getName(), league);
				teams.add(teamRepository.save(team));
			}
		});
		return teams;
	}

	private List<League> createTestLeagues(List<Season> seasons) {
		List<League> leagues = new ArrayList<>();
		seasons.forEach(season -> {
			leagues.add(leagueRepository.save(new League("Pierwsza liga", season)));
			leagues.add(leagueRepository.save(new League("Druga liga", season)));
		});
		return leagues;
	}

	private List<Match> createTestMatches(List<Season> seasons) {
		List<Match> matches = new ArrayList<>();
		seasons.forEach(season -> matches.addAll(timetableService.generate(season)));
		return matches;
	}

	private List<Season> createTestSeasons() {
		Season season = new Season("sezon 2018", "2018");
		season.setOpen(true);
		return Collections.singletonList(seasonRepository.save(season));
	}

}
