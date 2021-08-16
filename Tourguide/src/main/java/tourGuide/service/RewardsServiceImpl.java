package tourGuide.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tourGuide.beans.AttractionBean;
import tourGuide.beans.LocationBean;
import tourGuide.beans.VisitedLocationBean;
import tourGuide.proxies.GpsUtilProxy;
import tourGuide.proxies.RewardsProxy;
import tourGuide.user.User;
import tourGuide.user.UserReward;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class RewardsServiceImpl implements RewardsService {

	private static final Logger logger = LoggerFactory.getLogger(RewardsService.class);

	private final ExecutorService executorService = Executors.newFixedThreadPool(1000);

    private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
    private int defaultProximityBuffer = 10;
    private int proximityBuffer = defaultProximityBuffer;
    private int attractionProximityRange = 200;

    @Autowired
	private GpsUtilProxy gpsUtilProxy;

    @Autowired
	private RewardsProxy rewardsProxy;



	public RewardsServiceImpl(GpsUtilProxy gpsUtilProxy, RewardsProxy rewardsProxy) {
		this.gpsUtilProxy = gpsUtilProxy;
		this.rewardsProxy = rewardsProxy;
	}
	
	public void setProximityBuffer(int proximityBuffer) {
		this.proximityBuffer = proximityBuffer;
	}
	
	public void setDefaultProximityBuffer() {
		proximityBuffer = defaultProximityBuffer;
	}

	@Override
	public void calculateRewards(User user) {
		// List location visited
		List<VisitedLocationBean> userLocationBeanList = new ArrayList<>(user.getVisitedLocations());
		// List attraction
		List<AttractionBean>          attractionBeanList   = new ArrayList<>(gpsUtilProxy.getAttractions());

		// Check visited location for user
		for(VisitedLocationBean visitedLocationBean : userLocationBeanList) {
			// Check location for attractions
			for(AttractionBean attractionBean : attractionBeanList) {
				// if user already rewards for attraction
				if(user.getUserRewardList().stream().noneMatch(r -> r.attractionBean.getAttractionName().equals(attractionBean.getAttractionName()))) {
					// verify location of user a proximity ti location attraction
					if(nearAttraction(visitedLocationBean, attractionBean)) {
						// rewards is Ad
						user.addUserReward(new UserReward(visitedLocationBean, attractionBean, getRewardPoints(attractionBean, user)));
					}
				}
			}
		}
	}

	public void calculateRewardsWithThread(User user) {
		executorService.execute(new Runnable() {
			public void run() {
				calculateRewards (user);
			}
		});
	}

	public void shutdown() throws InterruptedException{
		//shutdown means the executor service takes no more incoming tasks.
		executorService.shutdown();
		try {
			// awaitTermination is invoked after a shutdown request.
			if (!executorService.awaitTermination(20, TimeUnit.MINUTES)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			executorService.shutdownNow();
		 }
	}

	@Override
	public boolean isWithinAttractionProximity(AttractionBean attractionBean, LocationBean locationBean) {
		return (getDistance(new LocationBean(attractionBean.getLongitude(), attractionBean.getLatitude()), locationBean) < attractionProximityRange);
	}

	@Override
	public boolean nearAttraction(VisitedLocationBean visitedLocationBean, AttractionBean attractionBean) {
		return (getDistance(visitedLocationBean.getLocation(), new LocationBean(attractionBean.getLongitude(), attractionBean.getLatitude())) < proximityBuffer);
	}

	@Override
	public int getRewardPoints(AttractionBean attractionBean, User user) {
		return rewardsProxy.getAttractionRewardPoints(attractionBean.getAttractionId(), user.getUserId());
	}

	@Override
	public double getDistance(LocationBean loc1, LocationBean loc2) {
        double lat1 = Math.toRadians(loc1.getLatitude());
        double lon1 = Math.toRadians(loc1.getLongitude());
        double lat2 = Math.toRadians(loc2.getLatitude());
        double lon2 = Math.toRadians(loc2.getLongitude());

        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                               + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
        return statuteMiles;
	}

}
