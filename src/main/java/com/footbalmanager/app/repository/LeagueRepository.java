package com.footbalmanager.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.footbalmanager.app.domain.League;

public interface LeagueRepository extends CrudRepository<League, Long> {

	Iterable<League> getLeaguesBySeasonId(Long seasonId);

}
