package com.footbalmanager.app.services;

import com.footbalmanager.app.domain.League;
import com.footbalmanager.app.dto.league.PatchLeagueRequestDto;
import com.footbalmanager.app.dto.league.PostLeagueRequestDto;
import com.footbalmanager.app.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("leagueService")
@Transactional
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    private LeagueRepository leagueRepository;

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
        //TODO: dto -> entity i save, nie piszę póki nie znamy pól
    }

    @Override
    public Iterable<League> save(Iterable<League> entities) {
        return leagueRepository.save(entities);
    }
}
