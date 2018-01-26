package com.footbalmanager.app.services;

import com.footbalmanager.app.abstraction.Service;
import com.footbalmanager.app.domain.Season;
import com.footbalmanager.app.dto.season.PatchSeasonRequestDto;
import com.footbalmanager.app.dto.season.PostSeasonRequestDto;
import com.footbalmanager.app.dto.season.SeasonRapportDto;

public interface SeasonService extends Service<Season> {
    void update(Long id, PatchSeasonRequestDto dto);

    void save(PostSeasonRequestDto dto);

    void close(Long id);

	SeasonRapportDto getRapport(Long id);
}
