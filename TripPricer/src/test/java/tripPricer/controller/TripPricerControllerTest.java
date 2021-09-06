package tripPricer.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tripPricer.controller.TripPricerController;
import tripPricer.service.TripPricerService;

import java.util.UUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TripPricerController.class)
@AutoConfigureMockMvc
@EnableWebMvc
public class TripPricerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripPricerService tripPricerService;

    @Test
    @DisplayName("getPriceTest")
    public void getPriceTest() throws Exception {

        mockMvc.perform(get("/tripPricer/getPrice/" + UUID.randomUUID() + "/" + UUID.randomUUID() + "/" + 1 + "/" + 1 + "/" + 1 + "/" + 1)).andExpect(status().isOk());

    }


}
