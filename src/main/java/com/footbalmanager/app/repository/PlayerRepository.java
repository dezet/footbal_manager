package com.footbalmanager.app.repository;

import com.footbalmanager.app.domain.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {

	Player findByUsername(String username);

}
