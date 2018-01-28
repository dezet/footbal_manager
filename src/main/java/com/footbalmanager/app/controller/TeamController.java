package com.footbalmanager.app.controller;

import com.footbalmanager.app.abstraction.BaseController;
import com.footbalmanager.app.dto.team.PatchTeamRequestDto;
import com.footbalmanager.app.dto.team.PostTeamRequestDto;
import com.footbalmanager.app.services.PlayerService;
import com.footbalmanager.app.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeamController extends BaseController {
    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @GetMapping("/teams")
    public ResponseEntity<?> getTeams() {
        return new ResponseEntity<>(teamService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<?> getTeam(@PathVariable Long id) {
        return new ResponseEntity<>(teamService.findOne(id), HttpStatus.OK);
    }

    @GetMapping("/teams/{id}/assign/{username}")
    public ResponseEntity<?> assignPlayer(@PathVariable Long id, @PathVariable String username) {
        return new ResponseEntity<>(teamService.assignPlayerToTeam(id, playerService.
                findByUsername(username).getId()), HttpStatus.OK);
    }

    @PostMapping("/teams")
    public ResponseEntity<?> saveTeam(@RequestBody PostTeamRequestDto dto) {
        teamService.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/teams/{id}")
    public ResponseEntity<?> updateTeam(@PathVariable Long id, @RequestBody PatchTeamRequestDto dto) {
        teamService.update(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/teams/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        teamService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
