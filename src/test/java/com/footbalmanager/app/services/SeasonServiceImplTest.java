package com.footbalmanager.app.services;

import com.footbalmanager.app.domain.Season;
import com.footbalmanager.app.repository.LeagueRepository;
import com.footbalmanager.app.repository.MatchRepository;
import com.footbalmanager.app.repository.SeasonRepository;
import com.footbalmanager.app.repository.TeamRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
public class SeasonServiceImplTest {

    @MockBean
    private SeasonRepository seasonRepository;
    @MockBean
    private MatchRepository matchRepository;
    @MockBean
    private LeagueRepository leagueRepository;
    @MockBean
    private TeamRepository teamRepository;
    @Autowired
    private SeasonService seasonService;
    private Season season;

    @Before
    public void setUp() {
        season = new Season("Dominik", "2017");
    }

    @Test
    public void whenSave_thenCorrect() {
        given(seasonRepository.save(season))
                .willReturn(season);
        seasonService.save(season);
        then(seasonRepository)
                .should(times(1))
                .save(season);
    }

    @Test
    public void whenFindOne_thenCorrect() {
        given(seasonRepository.findOne(10L))
                .willReturn(season);
        seasonService.findOne(10L);
        then(seasonRepository)
                .should(times(1))
                .findOne(10L);
    }

    @Test
    public void whenFindAll_thenCorrect() {
        given(seasonRepository.findAll())
                .willReturn(Arrays.asList(season));
        seasonService.findAll();
        then(seasonRepository)
                .should(times(1))
                .findAll();
    }

    @Test
    public void whenDeleteById_thenCorrect() {
        seasonService.delete(10L);
        then(seasonRepository)
                .should(times(1))
                .delete(10L);
    }

    @Test
    public void whenDeleteByEntity_thenCorrect() {
        seasonService.delete(season);
        then(seasonRepository)
                .should(times(1))
                .delete(season);
    }

    @TestConfiguration
    static class BaseServiceImplTestContextConfiguration {

        @Bean
        public SeasonService seasonService() {
            return new SeasonServiceImpl();
        }
    }
}


