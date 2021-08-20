package com.tourguide.gpsUtil.controller;

import gpsUtil.controller.GpsUtilController;
import gpsUtil.location.Attraction;
import gpsUtil.service.GpsUtilService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest (classes = GpsUtilController.class)
@AutoConfigureMockMvc
@EnableWebMvc
public class GpsUtilControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GpsUtilService gpsUtilService;

    @Test
    @DisplayName("getUserLocationTest")
    public void getUserLocationTest() throws Exception {

       mockMvc.perform(get("/gpsUtil/getUserLocation/" + UUID.randomUUID())).andExpect(status().isOk());

    }

    @Test
    @DisplayName("getAttractionsTest")
    public void getAttractionsTest() throws Exception {
        //GIVEN
        List<Attraction> attractionList = new ArrayList<>();
        //WHEN
        when(gpsUtilService.getAttractions()).thenReturn(attractionList);
        //THEN
        mockMvc.perform(get( "/gpsUtil/getAttractions")).andExpect(status().isOk());
        verify(gpsUtilService, times(1)).getAttractions();

    }

}
