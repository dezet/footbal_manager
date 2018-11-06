package com.footbalmanager.app.services;

import com.footbalmanager.app.domain.League;
import com.footbalmanager.app.domain.Player;
import com.footbalmanager.app.domain.Season;
import com.footbalmanager.app.domain.Team;
import com.footbalmanager.app.dto.team.PatchTeamRequestDto;
import com.footbalmanager.app.dto.team.PostTeamRequestDto;
import com.footbalmanager.app.repository.LeagueRepository;
import com.footbalmanager.app.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service("teamService")
@Transactional
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private PlayerService playerService;

    @Override
    public void update(Long leagueId, PatchTeamRequestDto dto) {
        //TODO: zostawiam na później
    }

    @Override
    public Team save(Team entity) {
        return teamRepository.save(entity);
    }

    @Override
    public Team findOne(Long id) {
        return teamRepository.findOne(id);
    }

    @Override
    public Collection<Team> findAll() {
        return (Collection<Team>) teamRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        teamRepository.delete(id);
    }

    @Override
    public void delete(Team entity) {
        teamRepository.delete(entity);
    }

    @Override
    public void save(PostTeamRequestDto dto) {
        League league = leagueRepository.findOne(dto.getLeagueId());
        Team team = new Team(dto.getName(), league);
        teamRepository.save(team);
    }

    @Override
    public Team assignPlayerToTeam(Long id, Long playerId) {
        Team team = teamRepository.findOne(id);
        Player player = playerService.findOne(playerId);
        Collection<Player> players = team.getPlayers();
        players.add(player);
        team.setPlayers(players);
        player.setTeam(team);
        save(team);
        playerService.save(player);
        return team;
    }

    @Override
    public Iterable<Team> save(Iterable<Team> entities) {
        return teamRepository.save(entities);
    }

    @Override
    public List<Team> findTeamsBySeason(Season season) {
        return teamRepository.findAllBySeason(season.getId());
    }
}
