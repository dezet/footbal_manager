package com.footbalmanager.app.controller;

import com.footbalmanager.app.abstraction.BaseController;
import com.footbalmanager.app.dto.league.PatchLeagueRequestDto;
import com.footbalmanager.app.dto.league.PostLeagueRequestDto;
import com.footbalmanager.app.services.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LeagueController  extends BaseController {
    @Autowired
    private LeagueService leagueService;


    @GetMapping("/leagues")
    public ResponseEntity<?> getLeagues() {
        return new ResponseEntity<>(leagueService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/leagues/{leagueId}")
    public ResponseEntity<?> getLeague(@PathVariable Long leagueId) {
        return new ResponseEntity<>(leagueService.findOne(leagueId), HttpStatus.OK);
    }

    @PatchMapping("/leagues/{leagueId}")
    public ResponseEntity<?> updateLeague(@PathVariable Long leagueId, @RequestBody PatchLeagueRequestDto dto) {
        leagueService.update(leagueId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/leagues/{leagueId}")
    public ResponseEntity<?> deleteLeague(@PathVariable Long leagueId) {
        leagueService.delete(leagueId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/leagues")
    public ResponseEntity<?> saveLeague(@RequestBody PostLeagueRequestDto dto) {
        leagueService.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
