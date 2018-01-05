package com.footbalmanager.app.services;

import com.footbalmanager.app.abstraction.Service;
import com.footbalmanager.app.domain.League;
import com.footbalmanager.app.dto.league.PatchLeagueRequestDto;
import com.footbalmanager.app.dto.league.PostLeagueRequestDto;

public interface LeagueService extends Service<League> {

    void update(Long leagueId, PatchLeagueRequestDto dto);

    void save(PostLeagueRequestDto dto);

}
