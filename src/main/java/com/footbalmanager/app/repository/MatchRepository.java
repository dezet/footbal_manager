package com.footbalmanager.app.repository;

import com.footbalmanager.app.domain.Match;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {
    @Query("select m from Match m left join m.league as l left join l.season as s where m.league = l and l.season = s and s.id = ?1")
    Iterable<Match> findBySeason(Long id);
}
