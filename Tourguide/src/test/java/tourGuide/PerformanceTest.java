package tourGuide;

import org.apache.commons.lang3.time.StopWatch;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Performance Test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PerformanceTest {
	
	/*
	 * A note on performance improvements:
	 *     
	 *     The number of users generated for the high volume tests can be easily adjusted via this method:
	 *     
	 *     		InternalTestHelper.setInternalUserNumber(100000);
	 *     
	 *     
	 *     These tests can be modified to suit new solutions, just as long as the performance metrics
	 *     at the end of the tests remains consistent. 
	 * 
	 *     These are performance metrics that we are trying to hit:
	 *     
	 *     highVolumeTrackLocation: 100,000 users within 15 minutes:
	 *     		assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
     *
     *     highVolumeGetRewards: 100,000 users within 20 minutes:
	 *          assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	 */

	@Autowired
	private GpsUtilProxy gpsUtilProxy;

	@Autowired
	private RewardsProxy rewardsProxy;

	@Autowired
	private TripPriceProxy tripPriceProxy;

	@Autowired
	private TourGuideServiceImpl tourGuideServiceImpl;

	@Autowired
	private RewardsServiceImpl rewardsServiceImpl;


	@Test
	@DisplayName("trackLocationTo100UsersTest")
	public void trackLocationTo100UsersTest() {

		RewardsServiceImpl rewardsServiceImpl = new RewardsServiceImpl(gpsUtilProxy, rewardsProxy);
		// Users should be incremented up to 100,000, and test finishes within 15 minutes (900 sec) (simple thread 8.9 sec for 100)
		InternalTestHelper.setInternalUserNumber(100);
		TourGuideServiceImpl tourGuideServiceImpl = new TourGuideServiceImpl(gpsUtilProxy,rewardsProxy,
				tripPriceProxy, rewardsServiceImpl);

		List<User> allUsers = new ArrayList<>(tourGuideServiceImpl.getAllUsers());

	    StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		for(User user : allUsers) {
			tourGuideServiceImpl.trackUserLocation(user);
		}
		stopWatch.stop();
		tourGuideServiceImpl.tracker.stopTracking();

		System.out.println("highVolumeTrackLocation: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");
		assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	}


	@Test
	public void getRewardsTo100UsersTest() {

		// Users should be incremented up to 100,000, and test finishes within 20 minutes (1200 sec) (simple thread 36.8 sec for 100)
		InternalTestHelper.setInternalUserNumber(100);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

	    AttractionBean attractionBean = gpsUtilProxy.getAttractions().get(0);
		List<User>     allUsers       = new ArrayList<>(tourGuideServiceImpl.getAllUsers());
		allUsers.forEach(u -> u.addToVisitedLocations(new VisitedLocationBean(u.getUserId(), new LocationBean(attractionBean.getLongitude(), attractionBean.getLatitude()), new Date())));

		allUsers.forEach(u -> rewardsServiceImpl.calculateRewards(u));

		for(User user : allUsers) {
			assertTrue(user.getUserRewardList().size() > 0);
		}
		stopWatch.stop();
		tourGuideServiceImpl.tracker.stopTracking();

		System.out.println("highVolumeGetRewards: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");
		assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	}
	
}
