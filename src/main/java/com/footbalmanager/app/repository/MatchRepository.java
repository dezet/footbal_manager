package com.footbalmanager.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.footbalmanager.app.domain.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {

}
