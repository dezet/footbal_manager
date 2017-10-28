package com.footbalmanager.app.repository;

import com.footbalmanager.app.BaseRepositoryTest;
import com.footbalmanager.app.domain.Player;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerRepositoryTests extends BaseRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    PlayerRepository playerRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void findAll_thenCorrect() {
        Player p = new Player("Dominik", "Dziąg");
        em.persist(p);
        assertThat(playerRepository.findAll().spliterator().getExactSizeIfKnown() == 1L);
    }

}
