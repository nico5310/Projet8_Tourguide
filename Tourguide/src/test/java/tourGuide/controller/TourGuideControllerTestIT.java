package tourGuide.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import tourGuide.helper.InternalTestHelper;
import tourGuide.service.RewardsService;
import tourGuide.service.TourGuideService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TourGuideController.class)
public class TourGuideControllerTestIT {

    @MockBean
    private TourGuideService tourGuideService;

    @MockBean
    private RewardsService rewardsService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void setUp() {
        InternalTestHelper.setInternalUserNumber(1);
    }

    @Test
    void index() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    void getLocationTest() throws Exception {
        mockMvc.perform(get("/getLocation").param("userName", "internalUser1")).andExpect(status().isOk());
    }

    @Test
    void getNearbyAttractionsTest() throws Exception {
        mockMvc.perform(get("/getNearbyAttractions").param("userName", "internalUser1")).andExpect(status().isOk());
    }

    @Test
    void getRewards() throws Exception {
        mockMvc.perform(get("/getRewards").param("userName", "internalUser1")).andExpect(status().isOk());
    }

    @Test
    void getAllCurrentLocationsTest() throws Exception {
        mockMvc.perform(get("/getAllCurrentLocations").param("userName", "internalUser1")).andExpect(status().isOk());
    }

    @Test
    void getTripDeals() throws Exception {
        mockMvc.perform(get("/getTripDeals").param("userName", "internalUser").param("tripDuration","1").param("numberOfAdults", "1").param("numberOfChildren", "0")).andExpect(status().isOk());
    }

}
