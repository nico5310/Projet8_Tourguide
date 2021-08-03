package tourGuide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tourGuide.proxies.GpsUtilProxy;
import tourGuide.proxies.RewardsProxy;
import tourGuide.service.RewardsService;

@Configuration
public class TourGuideModule {

	@Autowired
	GpsUtilProxy gpsUtilProxy;

	@Autowired
	RewardsProxy rewardsProxy;

	@Bean
	public RewardsService getRewardsService() {
		return new RewardsService(gpsUtilProxy, rewardsProxy);
	}

	
}
