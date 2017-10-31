package com.footbalmanager.app.repository;

import com.footbalmanager.app.domain.Match;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {


}
