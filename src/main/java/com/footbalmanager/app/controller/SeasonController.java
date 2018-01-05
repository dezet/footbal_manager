package com.footbalmanager.app.controller;

import com.footbalmanager.app.dto.season.PatchSeasonRequestDto;
import com.footbalmanager.app.dto.season.PostSeasonRequestDto;
import com.footbalmanager.app.services.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:8080")
public class SeasonController {
    @Autowired
    private SeasonService seasonService;

    @GetMapping("/seasons")
    public ResponseEntity<?> getMatches() {
        return new ResponseEntity<>(seasonService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/seasons/{seasonId}")
    public ResponseEntity<?> getMatch(@PathVariable Long seasonId) {
        return new ResponseEntity<>(seasonService.findOne(seasonId), HttpStatus.OK);
    }

    @PostMapping("/seasons")
    public ResponseEntity<?> postMatch(@RequestBody PostSeasonRequestDto dto) {
        //TODO: obsluga wyjątków
        seasonService.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/seasons/{seasonId}")
    public ResponseEntity<?> patchTeam(@PathVariable Long seasonId, @RequestBody PatchSeasonRequestDto dto) {
        //TODO: obsluga wyjątków
        seasonService.update(seasonId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/seasons/{seasonId}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long seasonId) {
        //TODO: obsluga wyjątków
        seasonService.delete(seasonId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
