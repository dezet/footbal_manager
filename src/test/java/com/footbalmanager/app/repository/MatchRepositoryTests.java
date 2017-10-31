package com.footbalmanager.app.repository;

import com.footbalmanager.app.BaseRepositoryTest;
import com.footbalmanager.app.domain.League;
import com.footbalmanager.app.domain.Match;
import com.footbalmanager.app.domain.Team;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchRepositoryTests extends BaseRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MatchRepository matchRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void findAll_thenCorrect() {
        League l = new League("LeagueA");
        Team t1 = new Team("team1", l);
        Team t2 = new Team("team2", l);
        Match m = new Match(t1, t2, LocalDateTime.now(), 0, 0, true, l);
        em.persist(m);
        assertThat(matchRepository.findAll().spliterator().getExactSizeIfKnown()).isEqualTo(1L);
        assertThat(matchRepository.findOne(m.getId()).getLeague()).isNotNull();
        assertThat(matchRepository.findOne(m.getId()).getHome()).isNotNull();
        assertThat(matchRepository.findOne(m.getId()).getAway()).isNotNull();
    }

}
