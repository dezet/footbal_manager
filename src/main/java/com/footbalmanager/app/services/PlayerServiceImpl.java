package com.footbalmanager.app.services;

import com.footbalmanager.app.domain.Player;
import com.footbalmanager.app.dto.player.PatchPlayerRequestDto;
import com.footbalmanager.app.dto.player.PostPlayerRequestDto;
import com.footbalmanager.app.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Collections.emptyList;

@Service("playerService")
@Transactional
public class PlayerServiceImpl implements PlayerService, UserDetailsService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
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
        Player player = new Player(dto.getFirstname(), dto.getLastname(), dto.getUsername(),
                bCryptPasswordEncoder.encode(dto.getPassword()), dto.getEmail());
        playerRepository.save(player);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Player player = playerRepository.findByUsername(username);
        if (player == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(player.getUsername(), player.getPassword(), emptyList());
    }

    @Override
    public Iterable<Player> save(Iterable<Player> entities) {
        return playerRepository.save(entities);
    }
}
