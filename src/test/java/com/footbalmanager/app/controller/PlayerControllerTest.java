package com.footbalmanager.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.footbalmanager.app.domain.Player;
import com.footbalmanager.app.dto.player.PostPlayerRequestDto;
import com.footbalmanager.app.services.PlayerService;
import com.footbalmanager.app.util.test.EntityTestManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @MockBean
    private PlayerService playerService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private EntityTestManager etm;

    @Before
    public void setUp() {
        this.etm = new EntityTestManager();
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = "USER")
    public void getPlayers_thenResponseIsOk() throws Exception {
        List<Player> players = Arrays.asList(etm.getPlayer("Dominik", "D"), etm.getPlayer("D", "Dominik"));
        given(playerService.findAll()).willReturn(players);
        this
                .mvc
                .perform(get("/players"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(players)));
    }


    @Test
    @WithMockUser(username = "test", password = "test", roles = "USER")
    public void getPlayer_thenResponseIsOk() throws Exception {
        final long playerId = 1L;
        Player p = etm.getPlayer("Dominik", "D");
        p.setId(playerId);
        given(playerService.findOne(playerId)).willReturn(p);
        this
                .mvc
                .perform(get("/players/" + playerId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(p)));
    }


    public void postPlayer_thenResponseIsOk() throws Exception {
        PostPlayerRequestDto pprd = new PostPlayerRequestDto();

    }

    public void patchPlayer_thenResponseIsOk() throws Exception {

    }

    public void deletePlayer_thenResponseIsOk() throws Exception {

    }


}
