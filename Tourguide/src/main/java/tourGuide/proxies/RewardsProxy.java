package tourGuide.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "Rewards_microservice", url = "localhost:8082")
public interface RewardsProxy {

    @GetMapping(value = "/getAttractionRewardPoints/{attractionId}/{userId}")
    int getAttractionRewardPoints(@PathVariable ("attractionId")  UUID attractionId, @PathVariable ("userId") UUID userId);

}
