package com.footbalmanager.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.footbalmanager.app.domain.Player;
import com.footbalmanager.app.dto.player.PostPlayerRequestDto;
import com.footbalmanager.app.repository.PlayerRepository;
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

    @MockBean
    private PlayerRepository playerRepositoryMock;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private EntityTestManager entityTestManager;

    @Before
    public void setUp() {
        entityTestManager = new EntityTestManager();
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = "USER")
    public void getPlayers_thenResponseIsOk() throws Exception {
        List<Player> players = Arrays
                .asList(entityTestManager.getPlayer("Dominik", "Domdomin", "domcio", "dddd111", "d@d.pl"),
                        entityTestManager.getPlayer("Dominik", "Domdomin", "domcio", "dddd111", "d@d.pl"));
        given(playerService.findAll()).willReturn(players);
        mockMvc.perform(get("/players")).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(players)));
    }


    @Test
    @WithMockUser(username = "test", password = "test", roles = "USER")
    public void getPlayer_thenResponseIsOk() throws Exception {
        long playerId = 1L;
        Player player = entityTestManager.getPlayer("Dominik", "Domdomin", "domcio", "dddd111", "d@d.pl");
        player.setId(playerId);
        given(playerService.findOne(playerId)).willReturn(player);
        mockMvc.perform(get("/players/" + playerId)).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(player)));
    }


    public void postPlayer_thenResponseIsOk() throws Exception {
        PostPlayerRequestDto pprd = new PostPlayerRequestDto();

    }

    public void patchPlayer_thenResponseIsOk() throws Exception {

    }

    public void deletePlayer_thenResponseIsOk() throws Exception {

    }


}
