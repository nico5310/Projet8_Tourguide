package tourGuide;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tourGuide.beans.ProviderBean;
import tourGuide.beans.VisitedLocationBean;
import tourGuide.dto.NearByAttractionDto;
import tourGuide.helper.InternalTestHelper;
import tourGuide.proxies.GpsUtilProxy;
import tourGuide.proxies.RewardsProxy;
import tourGuide.proxies.TripPriceProxy;
import tourGuide.service.RewardsServiceImpl;
import tourGuide.service.TourGuideServiceImpl;
import tourGuide.user.User;

import java.util.List;
import java.util.UUID;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TourGuideServiceImplTest {

	@Autowired
    private GpsUtilProxy gpsUtilProxy;
	@Autowired
    private  RewardsProxy rewardsProxy;
	@Autowired
	private TripPriceProxy tripPriceProxy;
    @Autowired
    private  TourGuideServiceImpl tourGuideServiceImpl;


	@Test // OK
	@DisplayName("getUserLocationTest")
	public void getUserLocationTest() {

		InternalTestHelper.setInternalUserNumber(0);
        TourGuideServiceImpl tourGuideServiceImpl = new TourGuideServiceImpl(gpsUtilProxy,rewardsProxy, tripPriceProxy);
		User                user            = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");

		VisitedLocationBean visitedLocationBean = tourGuideServiceImpl.trackUserLocation(user);
		tourGuideServiceImpl.tracker.stopTracking();

		Assertions.assertTrue(visitedLocationBean.getUserId().equals(user.getUserId()));
	}

	@Test // OK
	@DisplayName("addUserTest")
	public void addUserTest() {

        RewardsServiceImpl rewardsServiceImpl = new RewardsServiceImpl(gpsUtilProxy, rewardsProxy);
		InternalTestHelper.setInternalUserNumber(0);
        TourGuideServiceImpl tourGuideServiceImpl = new TourGuideServiceImpl(gpsUtilProxy,rewardsProxy, tripPriceProxy);

		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		User user2 = new User(UUID.randomUUID(), "jon2", "000", "jon2@tourGuide.com");

		tourGuideServiceImpl.addUser(user);
		tourGuideServiceImpl.addUser(user2);

		User retrivedUser  = tourGuideServiceImpl.getUser(user.getUserName());
		User retrivedUser2 = tourGuideServiceImpl.getUser(user2.getUserName());

		tourGuideServiceImpl.tracker.stopTracking();

		Assertions.assertEquals(user, retrivedUser);
		Assertions.assertEquals(user2, retrivedUser2);
	}

	@Test // OK
	@DisplayName("getAllUsersTest")
	public void getAllUsersTest() {

        RewardsServiceImpl rewardsServiceImpl = new RewardsServiceImpl(gpsUtilProxy, rewardsProxy);
		InternalTestHelper.setInternalUserNumber(0);
        TourGuideServiceImpl tourGuideServiceImpl = new TourGuideServiceImpl(gpsUtilProxy,rewardsProxy, tripPriceProxy);

		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		User user2 = new User(UUID.randomUUID(), "jon2", "000", "jon2@tourGuide.com");

		tourGuideServiceImpl.addUser(user);
		tourGuideServiceImpl.addUser(user2);

		List<User> allUsers = tourGuideServiceImpl.getAllUsers();

		tourGuideServiceImpl.tracker.stopTracking();

		Assertions.assertTrue(allUsers.contains(user));
		Assertions.assertTrue(allUsers.contains(user2));
	}

	@Test // OK
	@DisplayName("trackUserTest")
	public void trackUserTest() {

        RewardsServiceImpl rewardsServiceImpl = new RewardsServiceImpl(gpsUtilProxy, rewardsProxy);
        InternalTestHelper.setInternalUserNumber(0);
        TourGuideServiceImpl tourGuideServiceImpl = new TourGuideServiceImpl(gpsUtilProxy,rewardsProxy, tripPriceProxy);

		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
        VisitedLocationBean      visitedLocationBean      = tourGuideServiceImpl.trackUserLocation(user);

		tourGuideServiceImpl.tracker.stopTracking();

		Assertions.assertEquals(user.getUserId(), visitedLocationBean.getUserId());
	}


	@Test // OK
	@DisplayName("getNearbyAttractionsTest")
	public void getNearbyAttractionsTest() {

        RewardsServiceImpl rewardsServiceImpl = new RewardsServiceImpl(gpsUtilProxy, rewardsProxy);
        InternalTestHelper.setInternalUserNumber(0);
        TourGuideServiceImpl tourGuideServiceImpl = new TourGuideServiceImpl(gpsUtilProxy,rewardsProxy, tripPriceProxy);

        User user  = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		VisitedLocationBean      visitedLocationBean      = tourGuideServiceImpl.trackUserLocation(user);

		List<NearByAttractionDto> attractions = tourGuideServiceImpl.getNearByAttractions(visitedLocationBean, user);
		tourGuideServiceImpl.tracker.stopTracking();

		Assertions.assertEquals(5, attractions.size());
	}

	@Test // Ok
	@DisplayName("getTripDealsTest")
	public void getTripDealsTest() {

        RewardsServiceImpl rewardsServiceImpl = new RewardsServiceImpl(gpsUtilProxy, rewardsProxy);
        InternalTestHelper.setInternalUserNumber(0);
        TourGuideServiceImpl tourGuideServiceImpl = new TourGuideServiceImpl(gpsUtilProxy,rewardsProxy, tripPriceProxy);

		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");

		List<ProviderBean> providerList = tourGuideServiceImpl.getTripDeals(user, 1, 1, 1);

		tourGuideServiceImpl.tracker.stopTracking();

		Assertions.assertEquals(5, providerList.size());
	}
	
	
}
