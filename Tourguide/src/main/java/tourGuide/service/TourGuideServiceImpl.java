package tourGuide.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tourGuide.beans.AttractionBean;
import tourGuide.beans.LocationBean;
import tourGuide.beans.ProviderBean;
import tourGuide.beans.VisitedLocationBean;
import tourGuide.dto.NearByAttractionDto;
import tourGuide.helper.InternalTestHelper;
import tourGuide.proxies.GpsUtilProxy;
import tourGuide.proxies.RewardsProxy;
import tourGuide.proxies.TripPriceProxy;
import tourGuide.tracker.Tracker;
import tourGuide.user.User;
import tourGuide.user.UserReward;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.IntStream;

@Service
public class TourGuideServiceImpl implements TourGuideService {

    private final Logger logger = LoggerFactory.getLogger(TourGuideService.class);


    private GpsUtilProxy gpsUtilProxy;

    private RewardsProxy rewardsProxy;

    private TripPriceProxy tripPriceProxy;

    private  RewardsService rewardsService;

    public Tracker tracker;

    boolean testMode = true;


    public TourGuideServiceImpl(GpsUtilProxy gpsUtilProxy, RewardsService rewardsService) {
        this.gpsUtilProxy   = gpsUtilProxy;
        this.rewardsService = rewardsService;

        if (testMode) {
            logger.info("TestMode enabled");
            logger.debug("Initializing users");
            initializeInternalUsers();
            logger.debug("Finished initializing users");
        }
        tracker = new Tracker(this);
        addShutDownHook();
    }


    public List<UserReward> getUserRewards(User user) {

        return user.getUserRewardList();
    }


    public VisitedLocationBean getUserLocation(User user) {

        VisitedLocationBean visitedLocationBean = (user.getVisitedLocations()
                                               .size() > 0) ? user.getLastVisitedLocation() : trackUserLocation(user);
        return visitedLocationBean;
    }

    public User getUser(String userName) {

        return internalUserMap.get(userName);
    }

    public List<User> getAllUsers() {

        return new ArrayList<>(internalUserMap.values());
    }

    public void addUser(User user) {

        if (!internalUserMap.containsKey(user.getUserName())) {
            internalUserMap.put(user.getUserName(), user);
        }
    }

    public List<ProviderBean> getTripDeals(User user) {

        int cumulatativeRewardPoints = user.getUserRewardList().stream().mapToInt(i -> i.getRewardPoints()).sum();
        List<ProviderBean> providerBeanList = tripPriceProxy.getPrice(
                tripPricerApiKey,
                user.getUserId(),
                user.getUserPreferences().getNumberOfAdults(),
                user.getUserPreferences().getNumberOfChildren(),
                user.getUserPreferences().getTripDuration(),
                cumulatativeRewardPoints);
        user.setTripDealsBeans(providerBeanList);
        return providerBeanList;
    }

    public VisitedLocationBean trackUserLocation(User user) {

        VisitedLocationBean visitedLocationBean = gpsUtilProxy.getUserLocation(user.getUserId());
        user.addToVisitedLocations(visitedLocationBean);
        rewardsService.calculateRewards(user);
        return visitedLocationBean;
    }

    public List<NearByAttractionDto> getNearByAttractions(VisitedLocationBean visitedLocationBean, User user) {

        TreeMap<Double, NearByAttractionDto> distanceUserToAttractionList = new TreeMap<>();
        for (AttractionBean attractionBean : gpsUtilProxy.getAttractions()) {
            double              distance            = rewardsService.getDistance(visitedLocationBean.getLocation(), attractionBean);
            NearByAttractionDto nearByAttractionDto = new NearByAttractionDto();
            nearByAttractionDto.setAttractionName(attractionBean.getAttractionName());
            nearByAttractionDto.setAttractionLongitude(attractionBean.getLongitude());
            nearByAttractionDto.setAttractionLatitude(attractionBean.getLatitude());
            nearByAttractionDto.setUserLongitude(visitedLocationBean.getLocation().getLongitude());
            nearByAttractionDto.setUserLatitude(visitedLocationBean.getLocation().getLatitude());
            nearByAttractionDto.setDistance(distance);
            nearByAttractionDto.setRewardPoints(rewardsService.getRewardPoints(attractionBean, user));
            distanceUserToAttractionList.put(distance, nearByAttractionDto);
        }
        Set<Map.Entry<Double, NearByAttractionDto>> entrySet            = distanceUserToAttractionList.entrySet();
        List<NearByAttractionDto>                   nearFiveAttractions = new ArrayList<>();

        for (Map.Entry<Double, NearByAttractionDto> entry : distanceUserToAttractionList.entrySet()) {
            for (int i = 0 ; nearFiveAttractions.size() < 5 ; i++) {
                nearFiveAttractions.add(entry.getValue());
                System.out.println(entry.getKey() + "->" + entry.getValue());
                break;
            }
        }
        return nearFiveAttractions;
    }

    private void addShutDownHook() {

        Runtime.getRuntime().addShutdownHook(new Thread() {

            public void run() {

                tracker.stopTracking();
            }
        });
    }

    /**********************************************************************************
     *
     * Methods Below: For Internal Testing
     *
     **********************************************************************************/
    private static final String            tripPricerApiKey = "test-server-api-key";
    // Database connection will be used for external users, but for testing purposes internal users are provided and stored in memory
    private final        Map<String, User> internalUserMap  = new HashMap<>();


    private void initializeInternalUsers() {

        IntStream.range(0, InternalTestHelper.getInternalUserNumber()).forEach(i -> {
            String userName = "internalUser" + i;
            String phone    = "000";
            String email    = userName + "@tourGuide.com";
            User   user     = new User(UUID.randomUUID(), userName, phone, email);
            generateUserLocationHistory(user);

            internalUserMap.put(userName, user);
        });
        logger.debug("Created " + InternalTestHelper.getInternalUserNumber() + " internal test users.");
    }


    private void generateUserLocationHistory(User user) {

        IntStream.range(0, 3).forEach(i -> {
            user.addToVisitedLocations(new VisitedLocationBean(user.getUserId(), new LocationBean(generateRandomLatitude(), generateRandomLongitude()), getRandomTime()));
        });
    }

    private double generateRandomLongitude() {

        double leftLimit  = -180;
        double rightLimit = 180;
        return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
    }

    private double generateRandomLatitude() {

        double leftLimit  = -85.05112878;
        double rightLimit = 85.05112878;
        return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
    }

    private Date getRandomTime() {

        LocalDateTime localDateTime = LocalDateTime.now().minusDays(new Random().nextInt(30));
        return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
    }

}
