package com.footbalmanager.app.services;

import com.footbalmanager.app.abstraction.Service;
import com.footbalmanager.app.domain.Match;
import com.footbalmanager.app.dto.match.PatchMatchRequestDto;
import com.footbalmanager.app.dto.match.PostMatchRequestDto;

public interface MatchService extends Service<Match> {
    void update(Long leagueId, PatchMatchRequestDto dto);

    void save(PostMatchRequestDto dto);

    Iterable<Match> findBySeason(Long seasonId);
}
