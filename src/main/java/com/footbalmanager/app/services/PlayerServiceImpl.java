package com.footbalmanager.app.services;

import com.footbalmanager.app.domain.Player;
import com.footbalmanager.app.dto.player.PatchPlayerRequestDto;
import com.footbalmanager.app.dto.player.PostPlayerRequestDto;
import com.footbalmanager.app.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("playerService")
@Transactional
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void update(Long playerId, PatchPlayerRequestDto dto) {
        //TODO: zostawiam na później
    }

    @Override
    public Player save(Player entity) {
        return playerRepository.save(entity);
    }

    @Override
    public Player findOne(Long id) {
        return playerRepository.findOne(id);
    }

    @Override
    public Iterable<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        playerRepository.delete(id);
    }

    @Override
    public void delete(Player entity) {
        playerRepository.delete(entity);
    }

    @Override
    public void save(PostPlayerRequestDto dto) {
        Player p = new Player(dto.getFirstname(), dto.getLastname());
        this.playerRepository.save(p);
    }
}
