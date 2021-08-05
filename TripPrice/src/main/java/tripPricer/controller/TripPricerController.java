package tripPricer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tripPricer.Provider;
import tripPricer.service.TripPricerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tripPricer")
public class TripPricerController {

    @Autowired
    private TripPricerService tripPricerService;

    @GetMapping ("/getPrice/{apiKey}/{attractionId}/{adults}/{children}/{nightsStay}/{rewardsPoints}")
    public List<Provider> getPrice(@PathVariable String apiKey, @PathVariable UUID attractionId, @PathVariable int adults, @PathVariable int children, @PathVariable int nightsStay, @PathVariable int rewardsPoints) {

        return tripPricerService.getPrice(apiKey, attractionId, adults, children, nightsStay, rewardsPoints);
    }

}
