package com.footbalmanager.app.service;


import com.footbalmanager.app.domain.League;
import com.footbalmanager.app.domain.Season;
import com.footbalmanager.app.repository.LeagueRepository;
import com.footbalmanager.app.services.LeagueService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LeagueServiceImplTest {
    @MockBean
    private LeagueRepository leagueRepository;
    @Autowired
    private LeagueService leagueService;

    private Season s;
    private League l;

    @Before
    public void setUp() {
        s = new Season("sezon1", "2017");
        l = new League("name", s);
    }

    @Test
    public void whenSave_thenCorrect() {
        given(leagueRepository.save(l))
                .willReturn(l);
        leagueService.save(l);
        then(leagueRepository)
                .should(times(1))
                .save(l);
    }

    @Test
    public void whenFindOne_thenCorrect() {
        given(leagueRepository.findOne(10L))
                .willReturn(l);
        leagueService.findOne(10L);
        then(leagueRepository)
                .should(times(1))
                .findOne(10L);
    }

    @Test
    public void whenFindAll_thenCorrect() {
        given(leagueRepository.findAll())
                .willReturn(Arrays.asList(l));
        leagueService.findAll();
        then(leagueRepository)
                .should(times(1))
                .findAll();
    }

    @Test
    public void whenDeleteById_thenCorrect() {
        leagueService.delete(10L);
        then(leagueRepository)
                .should(times(1))
                .delete(10L);
    }

    @Test
    public void whenDeleteByEntity_thenCorrect() {
        leagueService.delete(l);
        then(leagueRepository)
                .should(times(1))
                .delete(l);
    }
}


