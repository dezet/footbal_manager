package com.footbalmanager.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.footbalmanager.app.domain.League;
import com.footbalmanager.app.domain.Match;
import com.footbalmanager.app.domain.Season;
import com.footbalmanager.app.dto.season.PatchSeasonRequestDto;
import com.footbalmanager.app.dto.season.PostSeasonRequestDto;
import com.footbalmanager.app.dto.season.SeasonRapportDto;
import com.footbalmanager.app.repository.LeagueRepository;
import com.footbalmanager.app.repository.MatchRepository;
import com.footbalmanager.app.repository.SeasonRepository;
import com.footbalmanager.app.repository.TeamRepository;

@Service("seasonService")
@Transactional
public class SeasonServiceImpl implements SeasonService {

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
	private MatchRepository matchRepository;

	@Autowired
	private LeagueRepository leagueRepository;

	@Autowired
	private TeamRepository teamRepository;

    @Override
    public void update(Long id, PatchSeasonRequestDto dto) {
        //TODO: zostawiam na później
    }

    @Override
    public Season save(Season entity) {
        return seasonRepository.save(entity);
    }

    @Override
    public Season findOne(Long id) {
        return seasonRepository.findOne(id);
    }

    @Override
    public Iterable<Season> findAll() {
        return seasonRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        seasonRepository.delete(id);
    }

    @Override
    public void delete(Season entity) {
        seasonRepository.delete(entity);
    }

    @Override
    public void save(PostSeasonRequestDto dto) {
        Season newSeason = new Season(dto.getName(), dto.getYear());
		newSeason = seasonRepository.save(newSeason);
		if (dto.getBaseSeasonId() != null) {
			for (League league : leagueRepository.getLeaguesBySeasonId(dto.getBaseSeasonId())) {
				League newLeague = new League(league.getName(), newSeason);
				league.getTeams().forEach(team -> team.setLeague(newLeague));
				newLeague.setTeams(league.getTeams());
				league.setTeams(null);
				leagueRepository.save(league);
				leagueRepository.save(newLeague);
			}
		}
    }

    @Override
    public void close(Long id) {
        Season season = seasonRepository.findOne(id);
        season.setOpen(false);
        seasonRepository.save(season);
    }

	@Override
	public SeasonRapportDto getRapport(Long id) {
		Iterable<Match> matchesIterable = matchRepository.findBySeason(id);
		List<Match> matches = new ArrayList<>();
		matchesIterable.forEach(matches::add);
		SeasonRapportDto seasonRapportDto = new SeasonRapportDto();
		seasonRapportDto.setAwayGoals(matches.stream().mapToInt(Match::getAwayScore).sum());
		seasonRapportDto.setHomeGoals(matches.stream().mapToInt(Match::getHomeScore).sum());
		seasonRapportDto.setAwayLoses(
				(int) matches.stream().filter(match -> match.getAwayScore() < match.getHomeScore()).count());
		seasonRapportDto.setAwayWins(
				(int) matches.stream().filter(match -> match.getAwayScore() > match.getHomeScore()).count());
		seasonRapportDto.setHomeWins(
				(int) matches.stream().filter(match -> match.getHomeScore() > match.getAwayScore()).count());
		seasonRapportDto.setHomeLosses(
				(int) matches.stream().filter(match -> match.getHomeScore() < match.getAwayScore()).count());
		seasonRapportDto
				.setDraws((int) matches.stream().filter(match -> match.getHomeScore() == match.getAwayScore()).count());
		return seasonRapportDto;
	}

	@Override
    public Iterable<Season> save(Iterable<Season> entities) {
        return seasonRepository.save(entities);
    }

}
