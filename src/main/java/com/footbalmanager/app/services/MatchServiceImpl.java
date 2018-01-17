package com.footbalmanager.app.services;

import com.footbalmanager.app.domain.Match;
import com.footbalmanager.app.dto.match.PatchMatchRequestDto;
import com.footbalmanager.app.dto.match.PostMatchRequestDto;
import com.footbalmanager.app.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("matchService")
@Transactional
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public void update(Long leagueId, PatchMatchRequestDto dto) {

    }

    @Override
    public void save(PostMatchRequestDto dto) {

    }

    @Override
    public Match save(Match entity) {
        return matchRepository.save(entity);
    }

    @Override
    public Match findOne(Long id) {
        return matchRepository.findOne(id);
    }

    @Override
    public Iterable<Match> findAll() {
        return matchRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        matchRepository.delete(id);
    }

    @Override
    public void delete(Match entity) {
        matchRepository.delete(entity);
    }

    @Override
    public Iterable<Match> save(Iterable<Match> entities) {
        return matchRepository.save(entities);
    }
}
