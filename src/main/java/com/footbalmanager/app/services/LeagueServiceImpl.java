package com.footbalmanager.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.footbalmanager.app.domain.League;
import com.footbalmanager.app.domain.Season;
import com.footbalmanager.app.dto.league.PatchLeagueRequestDto;
import com.footbalmanager.app.dto.league.PostLeagueRequestDto;
import com.footbalmanager.app.repository.LeagueRepository;
import com.footbalmanager.app.repository.SeasonRepository;

@Service("leagueService")
@Transactional
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    private LeagueRepository leagueRepository;

	@Autowired
	private SeasonRepository seasonRepository;

    @Override
    public void update(Long leagueId, PatchLeagueRequestDto dto) {
        //TODO: zostawiam na później
    }

    @Override
    public League save(League entity) {
        return leagueRepository.save(entity);
    }

    @Override
    public League findOne(Long id) {
        return leagueRepository.findOne(id);
    }

    @Override
    public Iterable<League> findAll() {
        return leagueRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        leagueRepository.delete(id);
    }

    @Override
    public void delete(League entity) {
        leagueRepository.delete(entity);
    }

    @Override
    public void save(PostLeagueRequestDto dto) {
		Season season = seasonRepository.findOne(dto.getSeasonId());
		League league = new League(dto.getName(), season);
        leagueRepository.save(league);
    }

    @Override
    public Iterable<League> save(Iterable<League> entities) {
        return leagueRepository.save(entities);
    }
}
