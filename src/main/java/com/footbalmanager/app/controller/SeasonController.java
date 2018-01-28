package com.footbalmanager.app.controller;

import com.footbalmanager.app.abstraction.BaseController;
import com.footbalmanager.app.domain.Match;
import com.footbalmanager.app.dto.season.PatchSeasonRequestDto;
import com.footbalmanager.app.dto.season.PostSeasonRequestDto;
import com.footbalmanager.app.services.MatchService;
import com.footbalmanager.app.services.SeasonService;
import com.footbalmanager.app.services.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeasonController extends BaseController {
    @Autowired
    private SeasonService seasonService;

    @Autowired
    private MatchService matchService;

    @Autowired
    private TimetableService timetableService;


    @GetMapping("/seasons/{id}/timetable")
    public ResponseEntity<?> getTimetable(@PathVariable Long id) {
        return new ResponseEntity<>((List<Match>) matchService.findBySeason(id), HttpStatus.OK);
    }

    @GetMapping("/seasons/{id}/generate")
    public ResponseEntity<?> generateTimetable(@PathVariable Long id) {
        timetableService.generate(seasonService.findOne(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/seasons")
    public ResponseEntity<?> getSeasons() {
        return new ResponseEntity<>(seasonService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/seasons/{id}")
    public ResponseEntity<?> getSeason(@PathVariable Long id) {
        return new ResponseEntity<>(seasonService.findOne(id), HttpStatus.OK);
    }

    @GetMapping("/seasons/{id}/rapport")
    public ResponseEntity<?> getSeasonRapport(@PathVariable Long id) {
        return new ResponseEntity<>(seasonService.getRapport(id), HttpStatus.OK);
    }

    @PostMapping("/seasons")
    public ResponseEntity<?> saveSeason(@RequestBody PostSeasonRequestDto dto) {
        seasonService.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/seasons/{id}")
    public ResponseEntity<?> updateTeam(@PathVariable Long id, @RequestBody PatchSeasonRequestDto dto) {
        seasonService.update(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/seasons/{id}")
    public ResponseEntity<?> deleteSeason(@PathVariable Long id) {
        seasonService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/seasons/{id}/close")
    public ResponseEntity<?> closeSeason(@PathVariable Long id) {
        seasonService.close(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
