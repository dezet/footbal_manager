package com.footbalmanager.app.service;


import com.footbalmanager.app.domain.Player;
import com.footbalmanager.app.repository.PlayerRepository;
import com.footbalmanager.app.services.PlayerService;
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
public class PlayerServiceImplTest {

    @MockBean
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerService playerService;

    private Player p;

    @Before
    public void setUp() {
        p = new Player("Dominik", "Domdomin");
    }

    @Test
    public void whenSave_thenCorrect() {
        //given
        given(playerRepository.save(p))
                .willReturn(p);

        //when
        playerService.save(p);

        //then
        then(playerRepository)
                .should(times(1))
                .save(p);
    }

    @Test
    public void whenFindOne_thenCorrect() {
        //given
        given(playerRepository.findOne(10L))
                .willReturn(p);
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
                .willReturn(Arrays.asList(p));
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
        playerService.delete(p);
        //then
        then(playerRepository)
                .should(times(1))
                .delete(p);
    }
}


