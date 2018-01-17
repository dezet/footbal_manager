package com.footbalmanager.app.controller;

import com.footbalmanager.app.abstraction.BaseController;
import com.footbalmanager.app.dto.season.PatchSeasonRequestDto;
import com.footbalmanager.app.dto.season.PostSeasonRequestDto;
import com.footbalmanager.app.services.SeasonService;
import com.footbalmanager.app.services.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SeasonController extends BaseController {
    @Autowired
    private SeasonService seasonService;

    @Autowired
    private TimetableService timetableService;


    @GetMapping("/seasons/{seasonId}/generate")
    public ResponseEntity<?> generateTimetable(@PathVariable Long seasonId) {
        timetableService.generate(seasonService.findOne(seasonId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/seasons")
    public ResponseEntity<?> getMatches() {
        return new ResponseEntity<>(seasonService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/seasons/{seasonId}")
    public ResponseEntity<?> getMatch(@PathVariable Long seasonId) {
        return new ResponseEntity<>(seasonService.findOne(seasonId), HttpStatus.OK);
    }

    @PostMapping("/seasons")
    public ResponseEntity<?> saveMatch(@RequestBody PostSeasonRequestDto dto) {
        //TODO: obsluga wyjątków
        seasonService.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/seasons/{seasonId}")
    public ResponseEntity<?> updateTeam(@PathVariable Long seasonId, @RequestBody PatchSeasonRequestDto dto) {
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
