package com.footbalmanager.app.services;

import com.footbalmanager.app.domain.League;
import com.footbalmanager.app.domain.Season;
import com.footbalmanager.app.domain.Team;
import com.footbalmanager.app.repository.LeagueRepository;
import com.footbalmanager.app.repository.TeamRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
public class TeamServiceImplTest {

    @MockBean
    private TeamRepository teamRepository;
    @MockBean
    private LeagueRepository leagueRepository;
    @MockBean
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;
    private Team team;

    @Before
    public void setUp() {
        Season season = new Season("s1", "2017");
        League league = new League("name", season);
        team = new Team("Name1", league);
    }

    @Test
    public void whenSave_thenCorrect() {
        //given
        BDDMockito
                .given(teamRepository.save(team))
                .willReturn(team);

        //when
        teamService.save(team);

        //then
        BDDMockito
                .then(teamRepository)
                .should(BDDMockito.times(1))
                .save(team);
    }

    @Test
    public void whenFindOne_thenCorrect() {
        //given
        BDDMockito
                .given(teamRepository.findOne(10L))
                .willReturn(team);
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
                .willReturn(Arrays.asList(team));
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
        teamService.delete(team);
        //then
        BDDMockito
                .then(teamRepository)
                .should(BDDMockito.times(1))
                .delete(team);
    }

    @TestConfiguration
    static class BaseServiceImplTestContextConfiguration {

        @Bean
        public TeamService teamService() {
            return new TeamServiceImpl();
        }
    }

}


