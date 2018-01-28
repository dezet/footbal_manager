package com.footbalmanager.app.controller;

import com.footbalmanager.app.abstraction.BaseController;
import com.footbalmanager.app.domain.Player;
import com.footbalmanager.app.dto.team.PatchTeamRequestDto;
import com.footbalmanager.app.dto.team.PostTeamRequestDto;
import com.footbalmanager.app.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeamController extends BaseController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/teams")
    public ResponseEntity<?> getTeams() {
        return new ResponseEntity<>(teamService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<?> getTeam(@PathVariable Long id) {
        return new ResponseEntity<>(teamService.findOne(id), HttpStatus.OK);
    }

    @GetMapping("/teams/{id}/assign")
    public ResponseEntity<?> assignPlayer(@PathVariable Long id, @AuthenticationPrincipal Player currentUser) {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return new ResponseEntity<>(teamService.assignPlayerToTeam(id, currentUser.getId()), HttpStatus.OK);
    }

    @PostMapping("/teams")
    public ResponseEntity<?> saveTeam(@RequestBody PostTeamRequestDto dto) {
        teamService.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/teams/{id}")
    public ResponseEntity<?> updateTeam(@PathVariable Long id, @RequestBody PatchTeamRequestDto dto) {
        //TODO: obsluga wyjątków
        teamService.update(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/teams/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        //TODO: obsluga wyjątków
        teamService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
