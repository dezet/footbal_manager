package com.footbalmanager.app.services;

import com.footbalmanager.app.domain.League;
import com.footbalmanager.app.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LeagueServiceImpl implements LeagueService {

    @Autowired
    private LeagueRepository leagueRepository;

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
}
