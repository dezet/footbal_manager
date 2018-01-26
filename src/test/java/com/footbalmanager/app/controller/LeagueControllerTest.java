package com.footbalmanager.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.footbalmanager.app.domain.League;
import com.footbalmanager.app.services.LeagueService;
import com.footbalmanager.app.util.test.EntityTestManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
@WebMvcTest(LeagueController.class)
public class LeagueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LeagueService leagueService;

    @Autowired
    private ObjectMapper objectMapper;


    private EntityTestManager entityTestManager;

    @Before
    public void setUp() {
        this.entityTestManager = new EntityTestManager();
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = "USER")
    public void getLeagues_thenResponseIsOk() throws Exception {
        List<League> leagues = Arrays.asList(entityTestManager.getLeague(), entityTestManager.getLeague());
        given(leagueService.findAll()).willReturn(leagues);
        this
                .mockMvc
                .perform(get("/leagues").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(leagues)));
    }


    @Test
    @WithMockUser(username = "test", password = "test", roles = "USER")
    public void getLeague_thenResponseIsOk() throws Exception {
        Long leagueId = 1L;
        League l = entityTestManager.getLeague();
        l.setId(leagueId);
        given(leagueService.findOne(leagueId)).willReturn(l);
        this
                .mockMvc
                .perform(get("/leagues/" + leagueId).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(l)));
    }

    public void postLeague_thenResponseIsOk() throws Exception {

    }

    public void patchLeague_thenResponseIsOk() throws Exception {

    }

    public void deleteLeague_thenResponseIsOk() throws Exception {

    }


}
