package com.tourguide.rewards.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import rewards.controller.RewardsController;
import rewards.service.RewardsService;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest (classes = RewardsController.class)
@AutoConfigureMockMvc
@EnableWebMvc
public class RewardsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardsService gpsUtilService;

    @Test
    @DisplayName("getAttractionRewardPointsTest")
    public void getAttractionRewardPointsTest() throws Exception {

       mockMvc.perform(get("/rewards/getAttractionRewardPoints/" + UUID.randomUUID() + "/" + UUID.randomUUID())).andExpect(status().isOk());

    }


}
