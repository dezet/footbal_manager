package com.footbalmanager.app.controller;

import com.footbalmanager.app.abstraction.BaseController;
import com.footbalmanager.app.dto.team.PatchTeamRequestDto;
import com.footbalmanager.app.dto.team.PostTeamRequestDto;
import com.footbalmanager.app.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeamController extends BaseController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/teams")
    public ResponseEntity<?> getTeams() {
        return new ResponseEntity<>(teamService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/teams/{teamId}")
    public ResponseEntity<?> getTeam(@PathVariable Long teamId) {
        return new ResponseEntity<>(teamService.findOne(teamId), HttpStatus.OK);
    }

    @PostMapping("/teams")
    public ResponseEntity<?> saveTeam(@RequestBody PostTeamRequestDto dto) {
        teamService.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/teams/{teamId}")
    public ResponseEntity<?> updateTeam(@PathVariable Long teamId, @RequestBody PatchTeamRequestDto dto) {
        //TODO: obsluga wyjątków
        teamService.update(teamId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/teams/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long teamId) {
        //TODO: obsluga wyjątków
        teamService.delete(teamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
