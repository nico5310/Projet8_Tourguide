package tourGuide.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tourGuide.proxies.GpsUtilProxy;
import tourGuide.proxies.RewardsProxy;
import tourGuide.proxies.TripPriceProxy;
import tourGuide.service.RewardsServiceImpl;

@Configuration
public class TourGuideModule {

	@Autowired
	GpsUtilProxy gpsUtilProxy;

	@Autowired
	RewardsProxy rewardsProxy;

	@Autowired
	TripPriceProxy tripPriceProxy;

	@Bean
	public RewardsServiceImpl getRewardsService() {
		return new RewardsServiceImpl(gpsUtilProxy, rewardsProxy);
	}

	
}