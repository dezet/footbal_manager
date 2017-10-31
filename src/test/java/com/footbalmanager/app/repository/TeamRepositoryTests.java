package com.footbalmanager.app.repository;

import com.footbalmanager.app.BaseRepositoryTest;
import com.footbalmanager.app.domain.League;
import com.footbalmanager.app.domain.Team;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamRepositoryTests extends BaseRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    TeamRepository teamRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void findAll_thenCorrect() {
        League l = new League("leagueA");
        Team t = new Team("druzynaA", l);
        em.persist(t);
        assertThat(teamRepository.findAll().spliterator().getExactSizeIfKnown()).isEqualTo(1L);
        assertThat(teamRepository.findOne(t.getId()).getLeague()).isNotNull();
    }

}
