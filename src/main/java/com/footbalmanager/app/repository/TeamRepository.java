package com.footbalmanager.app.repository;

import com.footbalmanager.app.domain.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long> {
    @Query("select t from Team as t left join t.league as l left join l.season as s where t.league.id = l.id and l.season.id = s.id and s.id = ?1")
    List<Team> findAllBySeason(Long seasonId);
}
