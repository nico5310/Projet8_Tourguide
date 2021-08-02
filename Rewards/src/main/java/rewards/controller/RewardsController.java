package rewards.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rewards.service.RewardsService;

import java.util.UUID;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

    private static final Logger logger = LoggerFactory.getLogger(RewardsController.class);

    private  RewardsService rewardsService;

    @GetMapping ("/getAttractionRewardPoints/{attractionId}/{userId}")
    public int getAttractionRewardPoints(@PathVariable UUID attractionId, @PathVariable UUID userId) {

        logger.info("Returns calculating rewards for attraction visited");
        return rewardsService.getAttractionRewardPoints(attractionId, userId);
    }



}
