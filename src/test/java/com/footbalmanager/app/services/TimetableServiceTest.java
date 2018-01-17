package com.footbalmanager.app.services;

import com.footbalmanager.app.domain.League;
import com.footbalmanager.app.domain.Match;
import com.footbalmanager.app.domain.Season;
import com.footbalmanager.app.domain.Team;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class TimetableServiceTest {
    private List<Team> teams;
    private Season season;
    private League league;
    @Autowired
    private TimetableService timetableService;

    @MockBean
    private MatchService matchService;

    @Before
    public void setUp() {
        List<Team> tmp = new ArrayList<>();
        season = new Season("Season1", "2018");
        league = new League("League1", season);
        for (int i = 1; i <= 16; i++) {
            tmp.add(new Team("Team" + i, league));
        }
        teams = tmp;
    }

    @Test
    public void whenGenerate_thenCorrectTimetable() {
        //when
        List<Match> matches = timetableService.generate(season);
    }

    @TestConfiguration
    static class BaseServiceImplTestContextConfiguration {

        @Bean
        public TimetableService timetableSchedulerService() {
            return new TimetableService();
        }
    }

}


