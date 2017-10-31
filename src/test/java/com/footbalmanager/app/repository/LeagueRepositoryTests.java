package com.footbalmanager.app.repository;

import com.footbalmanager.app.BaseRepositoryTest;
import com.footbalmanager.app.domain.League;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

public class LeagueRepositoryTests extends BaseRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    LeagueRepository leagueRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void findAll_thenCorrect() {
        League l = new League("LigaA");
        em.persist(l);
        assertThat(leagueRepository.findAll().spliterator().getExactSizeIfKnown()).isEqualTo(1L);
    }

}
