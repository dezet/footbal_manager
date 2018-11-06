package com.footbalmanager.app.services;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.footbalmanager.app.domain.Match;
import com.footbalmanager.app.domain.Team;
import com.footbalmanager.app.repository.MatchRepository;
import com.footbalmanager.app.util.test.EntityTestManager;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
public class MatchServiceImplTest {

	@TestConfiguration
	static class BaseServiceImplTestContextConfiguration {

		@Bean
		public MatchService matchService() {
			return new MatchServiceImpl();
		}

		@Bean
		public EntityTestManager entityTestManager() {
			return new EntityTestManager();
		}
	}

    @MockBean
    private MatchRepository matchRepository;

    @Autowired
    private MatchService matchService;

    @Autowired
    private EntityTestManager entityTestManager;

    private Match match;

    @Before
    public void setUp() {
        Team home = entityTestManager.getTeam("home");
        Team away = entityTestManager.getTeam("away");
        match = entityTestManager.getMatch(home, away);
    }

    @Test
    public void whenSave_thenCorrect() {
        //given
        given(matchRepository.save(match))
                .willReturn(match);

        //when
        matchService.save(match);

        //then
        then(matchRepository)
                .should(times(1))
                .save(match);
    }

    @Test
    public void whenFindOne_thenCorrect() {
        //given
        given(matchRepository.findOne(10L))
                .willReturn(match);
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
                .willReturn(Arrays.asList(match));
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
        matchService.delete(match);
        //then
        then(matchRepository)
                .should(times(1))
                .delete(match);
    }
}


