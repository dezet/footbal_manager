package com.footbalmanager.app.service;


import com.footbalmanager.app.domain.League;
import com.footbalmanager.app.domain.Season;
import com.footbalmanager.app.repository.LeagueRepository;
import com.footbalmanager.app.services.LeagueService;
import com.footbalmanager.app.services.LeagueServiceImpl;
import com.sun.tools.javac.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LeagueServiceImplTest {
    @Mock
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
        //given
        BDDMockito
                .given(leagueRepository.save(l))
                .willReturn(l);
        //when
        leagueService.save(l);
        //then
        BDDMockito
                .then(leagueRepository)
                .should(BDDMockito.times(1))
                .save(l);
    }

    @Test
    public void whenFindOne_thenCorrect() {
        //given
        BDDMockito
                .given(leagueRepository.findOne(10L))
                .willReturn(l);
        //when
        leagueService.findOne(10L);
        //then
        BDDMockito
                .then(leagueRepository)
                .should(BDDMockito.times(1))
                .findOne(10L);
    }

    @Test
    public void whenFindAll_thenCorrect() {
        //given
        BDDMockito
                .given(leagueRepository.findAll())
                .willReturn(List.of(l));
        //when
        leagueService.findAll();
        //then
        BDDMockito
                .then(leagueRepository)
                .should(BDDMockito.times(1))
                .findAll();
    }

    @Test
    public void whenDeleteById_thenCorrect() {
        //given
        //when
        leagueService.delete(10L);
        //then
        BDDMockito
                .then(leagueRepository)
                .should(BDDMockito.times(1))
                .delete(1L);
    }

    @Test
    public void whenDeleteByEntity_thenCorrect() {
        //given

        //when
        leagueService.delete(l);
        //then
        BDDMockito
                .then(leagueRepository)
                .should(BDDMockito.times(1))
                .delete(l);
    }

    @TestConfiguration
    static class LeagueServiceImplTestContextConfiguration {
        @Bean
        public LeagueService deviceAliveServiceImpl() {
            return new LeagueServiceImpl();
        }
    }
}


