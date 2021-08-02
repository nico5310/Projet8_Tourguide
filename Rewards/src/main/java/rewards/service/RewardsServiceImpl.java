package rewards.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import rewardCentral.RewardCentral;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
public class RewardsServiceImpl implements RewardsService {

    private static final Logger logger = LoggerFactory.getLogger(RewardsServiceImpl.class);

    private static RewardCentral rewardCentral;


    @Override
    public int getAttractionRewardPoints(UUID attractionId, UUID userId) {

        return rewardCentral.getAttractionRewardPoints(attractionId, userId);
    }


}
