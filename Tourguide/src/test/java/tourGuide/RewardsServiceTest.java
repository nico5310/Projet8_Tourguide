package tourGuide;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tourGuide.beans.AttractionBean;
import tourGuide.beans.LocationBean;
import tourGuide.beans.VisitedLocationBean;
import tourGuide.helper.InternalTestHelper;
import tourGuide.proxies.GpsUtilProxy;
import tourGuide.proxies.RewardsProxy;
import tourGuide.proxies.TripPriceProxy;
import tourGuide.service.RewardsServiceImpl;
import tourGuide.service.TourGuideServiceImpl;
import tourGuide.user.User;
import tourGuide.user.UserReward;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@DisplayName("Reward Service Test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RewardsServiceTest {

    @Autowired
    private GpsUtilProxy gpsUtilProxy;

    @Autowired
    private RewardsProxy rewardsProxy;

    @Autowired
	private TripPriceProxy tripPriceProxy;

    @Autowired
    private TourGuideServiceImpl tourGuideServiceImpl;

	@Test
	@DisplayName("userGetRewardsTest")
	public void userGetRewardsTest() {
		RewardsServiceImpl rewardsServiceImpl = new RewardsServiceImpl(gpsUtilProxy, rewardsProxy);
		InternalTestHelper.setInternalUserNumber(0);
		TourGuideServiceImpl tourGuideServiceImpl = new TourGuideServiceImpl(gpsUtilProxy,rewardsProxy,
				tripPriceProxy, rewardsServiceImpl);

		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		AttractionBean attractionBean = gpsUtilProxy.getAttractions().get(0);
		user.addToVisitedLocations(new VisitedLocationBean(user.getUserId(), new LocationBean(attractionBean.getLongitude(), attractionBean.getLatitude()), new Date()));
		tourGuideServiceImpl.trackUserLocation(user);
		List<UserReward> userRewards = user.getUserRewardList();
		tourGuideServiceImpl.tracker.stopTracking();

		Assertions.assertEquals(1, userRewards.size());
	}

	@Test
	@DisplayName("isWithinAttractionProximityTest")
	public void isWithinAttractionProximityTest() {

		RewardsServiceImpl rewardsServiceImpl = new RewardsServiceImpl(gpsUtilProxy, rewardsProxy);

		AttractionBean attractionBean = gpsUtilProxy.getAttractions().get(0);

		Assertions.assertTrue(rewardsServiceImpl.isWithinAttractionProximity(attractionBean, new LocationBean(attractionBean.getLongitude(), attractionBean
				.getLatitude())));
	}


	@Test
	@DisplayName("nearAllAttractionsTest")
	public void nearAllAttractionsTest() {

		RewardsServiceImpl rewardsServiceImpl = new RewardsServiceImpl(gpsUtilProxy, rewardsProxy);
		rewardsServiceImpl.setProximityBuffer(Integer.MAX_VALUE);

		InternalTestHelper.setInternalUserNumber(1);
		rewardsServiceImpl.calculateRewards(tourGuideServiceImpl.getAllUsers().get(0));
		List<UserReward> userRewards = tourGuideServiceImpl.getUserRewards(tourGuideServiceImpl.getAllUsers().get(0));
		tourGuideServiceImpl.tracker.stopTracking();

		Assertions.assertEquals(gpsUtilProxy.getAttractions().size(), userRewards.size());
	}
	
}
