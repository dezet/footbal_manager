package com.footbalmanager.app.controller;

import com.footbalmanager.app.dto.league.PatchLeagueRequestDto;
import com.footbalmanager.app.dto.league.PostLeagueRequestDto;
import com.footbalmanager.app.services.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:8080")
public class LeagueController {
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
    public ResponseEntity<?> patchLeague(@PathVariable Long leagueId, @RequestBody PatchLeagueRequestDto dto) {
        leagueService.update(leagueId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/leagues/{leagueId}")
    public ResponseEntity<?> deleteLeague(@PathVariable Long leagueId) {
        leagueService.delete(leagueId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/leagues")
    public ResponseEntity<?> postLeague(@RequestBody PostLeagueRequestDto dto) {
        leagueService.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
