package com.footbalmanager.app.service;


import com.footbalmanager.app.domain.League;
import com.footbalmanager.app.domain.Season;
import com.footbalmanager.app.domain.Team;
import com.footbalmanager.app.repository.TeamRepository;
import com.footbalmanager.app.services.TeamService;
import com.footbalmanager.app.services.TeamServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamServiceImplTest {

    @MockBean
    private TeamRepository teamRepository;

    @Autowired
    private TeamService teamService;

    private Team t;

    @Before
    public void setUp() {
        Season s = new Season("s1", "2017");
        League l = new League("name", s);
        t = new Team("Name1", l);
    }

    @Test
    public void whenSave_thenCorrect() {
        //given
        BDDMockito
                .given(teamRepository.save(t))
                .willReturn(t);

        //when
        teamService.save(t);

        //then
        BDDMockito
                .then(teamRepository)
                .should(BDDMockito.times(1))
                .save(t);
    }

    @Test
    public void whenFindOne_thenCorrect() {
        //given
        BDDMockito
                .given(teamRepository.findOne(10L))
                .willReturn(t);
        //when
        teamService.findOne(10L);
        //then
        BDDMockito
                .then(teamRepository)
                .should(BDDMockito.times(1))
                .findOne(10L);
    }

    @Test
    public void whenFindAll_thenCorrect() {

        //given
        BDDMockito
                .given(teamRepository.findAll())
                .willReturn(Arrays.asList(t));
        //when
        teamService.findAll();
        //then
        BDDMockito
                .then(teamRepository)
                .should(BDDMockito.times(1))
                .findAll();
    }

    @Test
    public void whenDeleteById_thenCorrect() {
        //given
        //when
        teamService.delete(10L);
        //then
        BDDMockito
                .then(teamRepository)
                .should(BDDMockito.times(1))
                .delete(10L);
    }

    @Test
    public void whenDeleteByEntity_thenCorrect() {
        //given

        //when
        teamService.delete(t);
        //then
        BDDMockito
                .then(teamRepository)
                .should(BDDMockito.times(1))
                .delete(t);
    }

}


