package com.footbalmanager.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.footbalmanager.app.domain.Player;
import com.footbalmanager.app.repository.PlayerRepository;

import static java.util.Collections.emptyList;

@Service
public class PlayerService implements UserDetailsService {

	@Autowired
	PlayerRepository playerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Player player = playerRepository.findByUsername(username);
		if (player == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(player.getUsername(), player.getPassword(), emptyList());
	}
}
