package tourGuide.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

//import gpsUtil.GpsUtil;
//import gpsUtil.location.Attraction;
//import gpsUtil.location.Location;
//import gpsUtil.location.VisitedLocation;
import tourGuide.dto.NearByAttractionDto;
import tourGuide.helper.InternalTestHelper;
import tourGuide.tracker.Tracker;
import tourGuide.user.User;
import tourGuide.user.UserReward;
import tripPricer.Provider;
import tripPricer.TripPricer;

@Service
public class TourGuideService {
	private Logger logger = LoggerFactory.getLogger(TourGuideService.class);
//	private final GpsUtil gpsUtil;
//	private final RewardsService rewardsService;
	private final TripPricer tripPricer = new TripPricer();
//	public final Tracker tracker;
	boolean testMode = true;

	static {
		Locale.setDefault(Locale.US);
	}
	
//	public TourGuideService(GpsUtil gpsUtil, RewardsService rewardsService) {
//		this.gpsUtil = gpsUtil;
//		this.rewardsService = rewardsService;
//
//		if(testMode) {
//			logger.info("TestMode enabled");
//			logger.debug("Initializing users");
//			initializeInternalUsers();
//			logger.debug("Finished initializing users");
//		}
//		tracker = new Tracker(this);
//		addShutDownHook();
//	}
	
//	public List<UserReward> getUserRewards(User user) {
//		return user.getUserRewards();
//	}
//
//	public VisitedLocation getUserLocation(User user) {
//		VisitedLocation visitedLocation = (user.getVisitedLocations().size() > 0) ?
//			user.getLastVisitedLocation() :
//			trackUserLocation(user);
//		return visitedLocation;
//	}
	
//	public User getUser(String userName) {
//		return internalUserMap.get(userName);
//	}
//
//	public List<User> getAllUsers() {
//		return new ArrayList<>(internalUserMap.values());
//	}
//
//	public void addUser(User user) {
//		if(!internalUserMap.containsKey(user.getUserName())) {
//			internalUserMap.put(user.getUserName(), user);
//		}
//	}
//
//	public List<Provider> getTripDeals(User user) {
//		int cumulatativeRewardPoints = user.getUserRewards().stream().mapToInt(i -> i.getRewardPoints()).sum();
//		List<Provider> providers = tripPricer.getPrice(tripPricerApiKey, user.getUserId(), user.getUserPreferences().getNumberOfAdults(),
//				user.getUserPreferences().getNumberOfChildren(), user.getUserPreferences().getTripDuration(), cumulatativeRewardPoints);
//		user.setTripDeals(providers);
//		return providers;
//	}
	
//	public VisitedLocation trackUserLocation(User user) {
//		VisitedLocation visitedLocation = gpsUtil.getUserLocation(user.getUserId());
//		user.addToVisitedLocations(visitedLocation);
//		rewardsService.calculateRewards(user);
//		return visitedLocation;
//	}

//	public List<NearByAttractionDto> getNearByAttractions(VisitedLocation visitedLocation, User user) {
//
//		TreeMap<Double, NearByAttractionDto> distanceUserToAttractionList = new TreeMap<>();
//		for (Attraction attraction : gpsUtil.getAttractions()) {
//			double              distance            = rewardsService.getDistance(visitedLocation.location, attraction);
//			NearByAttractionDto nearByAttractionDto = new NearByAttractionDto();
//			nearByAttractionDto.setAttractionName(attraction.attractionName);
//			nearByAttractionDto.setAttractionLongitude(attraction.longitude);
//			nearByAttractionDto.setAttractionLatitude(attraction.latitude);
//			nearByAttractionDto.setUserLongitude(visitedLocation.location.longitude);
//			nearByAttractionDto.setUserLatitude(visitedLocation.location.latitude);
//			nearByAttractionDto.setDistance(distance);
//			nearByAttractionDto.setRewardPoints(rewardsService.getRewardPoints(attraction, user));
//			distanceUserToAttractionList.put(distance, nearByAttractionDto);
//		}
//		Set<Map.Entry<Double, NearByAttractionDto>>  entrySet            = distanceUserToAttractionList.entrySet();
//		List<NearByAttractionDto>                    nearFiveAttractions = new ArrayList<>();
//
//		for (Map.Entry<Double, NearByAttractionDto> entry : distanceUserToAttractionList.entrySet()) {
//			for (int i = 0 ; nearFiveAttractions.size() < 5 ; i++) {
//				nearFiveAttractions.add(entry.getValue());
//				System.out.println( entry.getKey() + "->" + entry.getValue());
//				break;
//			}
//		}
//		return nearFiveAttractions;
//	}
	
//	private void addShutDownHook() {
//		Runtime.getRuntime().addShutdownHook(new Thread() {
//		      public void run() {
//		        tracker.stopTracking();
//		      }
//		    });
//	}
	
	/**********************************************************************************
	 * 
	 * Methods Below: For Internal Testing
	 * 
	 **********************************************************************************/
//	private static final String tripPricerApiKey = "test-server-api-key";
//	// Database connection will be used for external users, but for testing purposes internal users are provided and stored in memory
//	private final Map<String, User> internalUserMap = new HashMap<>();
//	private void initializeInternalUsers() {
//		IntStream.range(0, InternalTestHelper.getInternalUserNumber()).forEach(i -> {
//			String userName = "internalUser" + i;
//			String phone = "000";
//			String email = userName + "@tourGuide.com";
//			User user = new User(UUID.randomUUID(), userName, phone, email);
//			generateUserLocationHistory(user);
//
//			internalUserMap.put(userName, user);
//		});
//		logger.debug("Created " + InternalTestHelper.getInternalUserNumber() + " internal test users.");
//	}
	
//	private void generateUserLocationHistory(User user) {
//		IntStream.range(0, 3).forEach(i-> {
//			user.addToVisitedLocations(new VisitedLocation(user.getUserId(), new Location(generateRandomLatitude(), generateRandomLongitude()), getRandomTime()));
//		});
//	}
	
	private double generateRandomLongitude() {
		double leftLimit = -180;
	    double rightLimit = 180;
	    return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
	}
	
	private double generateRandomLatitude() {
		double leftLimit = -85.05112878;
	    double rightLimit = 85.05112878;
	    return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
	}
	
	private Date getRandomTime() {
		LocalDateTime localDateTime = LocalDateTime.now().minusDays(new Random().nextInt(30));
	    return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
	}
	
}