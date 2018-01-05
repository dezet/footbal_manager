package com.footbalmanager.app.services;

import com.footbalmanager.app.abstraction.Service;
import com.footbalmanager.app.domain.Team;
import com.footbalmanager.app.dto.team.PatchTeamRequestDto;
import com.footbalmanager.app.dto.team.PostTeamRequestDto;

public interface TeamService extends Service<Team> {
    void update(Long leagueId, PatchTeamRequestDto dto);

    void save(PostTeamRequestDto dto);
}
