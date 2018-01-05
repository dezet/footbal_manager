package com.footbalmanager.app.controller;

import com.footbalmanager.app.dto.player.PatchPlayerRequestDto;
import com.footbalmanager.app.dto.player.PostPlayerRequestDto;
import com.footbalmanager.app.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin("http://localhost:8080")
public class PlayerController {
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
    public ResponseEntity<?> postPlayer(@RequestBody PostPlayerRequestDto dto) {
        //TODO: obsluga wyjątków
        playerService.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/players/{playerId}")
    public ResponseEntity<?> patchPlayer(@PathVariable Long playerId, @RequestBody PatchPlayerRequestDto dto) {
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
}
