package com.footbalmanager.app.controller;

import com.footbalmanager.app.abstraction.BaseController;
import com.footbalmanager.app.domain.Match;
import com.footbalmanager.app.dto.match.PatchMatchRequestDto;
import com.footbalmanager.app.dto.match.PostMatchRequestDto;
import com.footbalmanager.app.dto.match.PostUpdateScoreRequestDto;
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

    @GetMapping("/matches/{id}")
    public ResponseEntity<?> getMatch(@PathVariable Long id) {
        return new ResponseEntity<>(matchService.findOne(id), HttpStatus.OK);
    }

    @PostMapping("/matches")
    public ResponseEntity<?> saveMatch(@RequestBody PostMatchRequestDto dto) {
        //TODO: obsluga wyjątków
        matchService.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/matches/{id}")
    public ResponseEntity<?> updateMatch(@PathVariable Long id, @RequestBody PatchMatchRequestDto dto) {
        //TODO: obsluga wyjątków
        matchService.update(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/matches/{id}")
    public ResponseEntity<?> deleteMatch(@PathVariable Long id) {
        //TODO: obsluga wyjątków
        matchService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("matches/{id}/score")
    public ResponseEntity<?> updateScore(@PathVariable Long id, @RequestBody PostUpdateScoreRequestDto dto) {
        Match updatedMatch = matchService.findOne(id);
        updatedMatch.setAwayScore(dto.getAwayScore());
        updatedMatch.setHomeScore(dto.getHomeScore());
        Match m = matchService.save(updatedMatch);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
