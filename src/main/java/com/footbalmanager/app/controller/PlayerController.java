package com.footbalmanager.app.controller;

import com.footbalmanager.app.domain.Player;
import com.footbalmanager.app.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {
    @Autowired
    PlayerRepository playerRepository;

    @RequestMapping("/players")
    public List<Player> getPlayers() {
        playerRepository.save(new Player("Domino", "Domino"));
        playerRepository.save(new Player("Ciao", "Bambino"));
        return (List<Player>) playerRepository.findAll();
    }
}
