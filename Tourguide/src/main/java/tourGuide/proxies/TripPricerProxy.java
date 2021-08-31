package tourGuide.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tourGuide.beans.ProviderBean;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "TripPricer-microservice", url = "localhost:8083")
public interface TripPricerProxy {

    @GetMapping("/tripPricer/getPrice/{apiKey}/{attractionId}/{adults}/{children}/{nightsStay}/{rewardsPoints}")
    List<ProviderBean> getPrice(@PathVariable String apiKey, @PathVariable UUID attractionId, @PathVariable int adults, @PathVariable int children, @PathVariable int nightsStay, @PathVariable int rewardsPoints);

}
