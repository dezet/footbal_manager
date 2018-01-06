package com.footbalmanager.app.controller;

import com.footbalmanager.app.abstraction.BaseController;
import com.footbalmanager.app.dto.match.PatchMatchRequestDto;
import com.footbalmanager.app.dto.match.PostMatchRequestDto;
import com.footbalmanager.app.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MatchController extends BaseController {
    @Autowired
    private MatchService matchService;

    @GetMapping("/matches")
    public ResponseEntity<?> getMatches() {
        return new ResponseEntity<>(matchService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/matches/{matchId}")
    public ResponseEntity<?> getMatch(@PathVariable Long matchId) {
        return new ResponseEntity<>(matchService.findOne(matchId), HttpStatus.OK);
    }

    @PostMapping("/matches")
    public ResponseEntity<?> saveMatch(@RequestBody PostMatchRequestDto dto) {
        //TODO: obsluga wyjątków
        matchService.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/matches/{matchId}")
    public ResponseEntity<?> updateTeam(@PathVariable Long matchId, @RequestBody PatchMatchRequestDto dto) {
        //TODO: obsluga wyjątków
        matchService.update(matchId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/matches/{matchId}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long matchId) {
        //TODO: obsluga wyjątków
        matchService.delete(matchId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
