package com.footbalmanager.app.services;

import com.footbalmanager.app.abstraction.Service;
import com.footbalmanager.app.domain.Player;
import com.footbalmanager.app.dto.player.PatchPlayerRequestDto;
import com.footbalmanager.app.dto.player.PostPlayerRequestDto;

public interface PlayerService extends Service<Player> {
    void update(Long playerId, PatchPlayerRequestDto dto);

    void save(PostPlayerRequestDto dto);
}
