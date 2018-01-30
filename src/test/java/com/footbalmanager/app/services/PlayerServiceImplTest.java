package com.footbalmanager.app.services;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.footbalmanager.app.domain.Player;
import com.footbalmanager.app.repository.PlayerRepository;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
public class PlayerServiceImplTest {

	@TestConfiguration
	static class BaseServiceImplTestContextConfiguration {

		@Bean
		public PlayerService playerService() {
			return new PlayerServiceImpl();
		}
	}

    @MockBean
    private PlayerRepository playerRepository;

	@MockBean
	private BCryptPasswordEncoder encoder;

    @Autowired
    private PlayerService playerService;

    private Player player;

    @Before
    public void setUp() {
		player = new Player("Dominik", "Domdomin", "domcio", "dddd111", "d@d.pl");
	}

    @Test
    public void whenSave_thenCorrect() {
        //given
        given(playerRepository.save(player))
                .willReturn(player);

        //when
        playerService.save(player);

        //then
        then(playerRepository)
                .should(times(1))
                .save(player);
    }

    @Test
    public void whenFindOne_thenCorrect() {
        //given
        given(playerRepository.findOne(10L))
                .willReturn(player);
        //when
        playerService.findOne(10L);
        //then
        then(playerRepository)
                .should(times(1))
                .findOne(10L);
    }

    @Test
    public void whenFindAll_thenCorrect() {
        //given
        given(playerRepository.findAll())
                .willReturn(Arrays.asList(player));
        //when
        playerService.findAll();
        //then
        then(playerRepository)
                .should(times(1))
                .findAll();
    }

    @Test
    public void whenDeleteById_thenCorrect() {
        //given

        //when
        playerService.delete(10L);
        //then
        then(playerRepository)
                .should(times(1))
                .delete(10L);
    }

    @Test
    public void whenDeleteByEntity_thenCorrect() {
        //given

        //when
        playerService.delete(player);
        //then
        then(playerRepository)
                .should(times(1))
                .delete(player);
    }
}


