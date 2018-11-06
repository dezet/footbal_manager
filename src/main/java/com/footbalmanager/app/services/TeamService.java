package com.footbalmanager.app.services;

import com.footbalmanager.app.abstraction.Service;
import com.footbalmanager.app.domain.Season;
import com.footbalmanager.app.domain.Team;
import com.footbalmanager.app.dto.team.PatchTeamRequestDto;
import com.footbalmanager.app.dto.team.PostTeamRequestDto;

import java.util.List;

public interface TeamService extends Service<Team> {
    void update(Long leagueId, PatchTeamRequestDto dto);

    void save(PostTeamRequestDto dto);

    List<Team> findTeamsBySeason(Season season);

    Team assignPlayerToTeam(Long id, Long playerId);
}
