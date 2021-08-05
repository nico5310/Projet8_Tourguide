package rewards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rewards.service.RewardsService;

import java.util.UUID;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

    @Autowired
    private  RewardsService rewardsService;

    @GetMapping ("/getAttractionRewardPoints/{attractionId}/{userId}")
    public int getAttractionRewardPoints(@PathVariable ("attractionId")  UUID attractionId, @PathVariable ("userId") UUID userId) {

        return rewardsService.getAttractionRewardPoints(attractionId, userId);
    }



}
