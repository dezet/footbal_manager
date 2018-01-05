package com.footbalmanager.app.service;


import com.footbalmanager.app.domain.Match;
import com.footbalmanager.app.domain.Team;
import com.footbalmanager.app.repository.MatchRepository;
import com.footbalmanager.app.services.MatchService;
import com.footbalmanager.app.util.test.EntityTestManager;
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
public class MatchServiceImplTest {

    @MockBean
    private MatchRepository matchRepository;

    @Autowired
    private MatchService matchService;

    @Autowired
    private EntityTestManager entityTestManager;

    private Match m;

    @Before
    public void setUp() {
        Team home = entityTestManager.getTeam("home");
        Team away = entityTestManager.getTeam("away");
        m = entityTestManager.getMatch(home, away);
    }

    @Test
    public void whenSave_thenCorrect() {
        //given
        given(matchRepository.save(m))
                .willReturn(m);

        //when
        matchService.save(m);

        //then
        then(matchRepository)
                .should(times(1))
                .save(m);
    }

    @Test
    public void whenFindOne_thenCorrect() {
        //given
        given(matchRepository.findOne(10L))
                .willReturn(m);
        //when
        matchService.findOne(10L);
        //then
        then(matchRepository)
                .should(times(1))
                .findOne(10L);
    }

    @Test
    public void whenFindAll_thenCorrect() {
        //given
        given(matchRepository.findAll())
                .willReturn(Arrays.asList(m));
        //when
        matchService.findAll();
        //then
        then(matchRepository)
                .should(times(1))
                .findAll();
    }

    @Test
    public void whenDeleteById_thenCorrect() {
        //given

        //when
        matchService.delete(10L);
        //then
        then(matchRepository)
                .should(times(1))
                .delete(10L);
    }

    @Test
    public void whenDeleteByEntity_thenCorrect() {
        //given

        //when
        matchService.delete(m);
        //then
        then(matchRepository)
                .should(times(1))
                .delete(m);
    }
}


