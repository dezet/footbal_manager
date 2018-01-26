package com.footbalmanager.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.footbalmanager.app.abstraction.BaseController;
import com.footbalmanager.app.domain.Player;
import com.footbalmanager.app.dto.player.PatchPlayerRequestDto;
import com.footbalmanager.app.dto.player.PostPlayerRequestDto;
import com.footbalmanager.app.repository.PlayerRepository;
import com.footbalmanager.app.services.PlayerService;


@RestController
public class PlayerController extends BaseController {
    @Autowired
	PlayerRepository playerRepository;
    @Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private PlayerService playerService;

    @GetMapping("/players")
    public ResponseEntity<?> getPlayers() {
        return new ResponseEntity<>(playerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/players/{playerId}")
    public ResponseEntity<?> getPlayer(@PathVariable Long playerId) {
        return new ResponseEntity<>(playerService.findOne(playerId), HttpStatus.OK);
    }

    @PostMapping("/players")
    public ResponseEntity<?> savePlayer(@RequestBody PostPlayerRequestDto dto) {
        //TODO: obsluga wyjątków
        playerService.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/players/{playerId}")
    public ResponseEntity<?> updatePlayer(@PathVariable Long playerId, @RequestBody PatchPlayerRequestDto dto) {
        //TODO: obsluga wyjątków
        playerService.update(playerId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/players/{playerId}")
    public ResponseEntity<?> deletePlayer(@PathVariable Long playerId) {
		//TODO: obsluga wyjątków
		playerService.delete(playerId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@RequestBody PostPlayerRequestDto player) {
        //TODO: obsługa wyjątków
        playerService.save(player);
        return new ResponseEntity<>(HttpStatus.OK);
	}
}
