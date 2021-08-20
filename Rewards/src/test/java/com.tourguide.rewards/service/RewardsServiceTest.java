package com.tourguide.rewards.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rewards.service.RewardsServiceImpl;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ContextConfiguration(classes = {RewardsServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class RewardsServiceTest {

    @Mock
    private RewardsServiceImpl rewardsServiceImpl;

    @Test
    @DisplayName("getAttractionRewardPointsTest")
    public void getAttractionRewardPointsTest() throws Exception {

        when(rewardsServiceImpl.getAttractionRewardPoints(any(UUID.class), any(UUID.class))).thenReturn(200);

        assertEquals(rewardsServiceImpl.getAttractionRewardPoints(UUID.randomUUID(), UUID.randomUUID()), 200);

    }


}
