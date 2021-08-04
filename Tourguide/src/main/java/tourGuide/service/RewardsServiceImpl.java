package tourGuide.service;

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

@Service
public class RewardsServiceImpl implements RewardsService {
    private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

	// proximity in miles
    private int defaultProximityBuffer = 10;
	private int proximityBuffer = defaultProximityBuffer;
	private int attractionProximityRange = 200;

	private GpsUtilProxy gpsUtilProxy;
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

	public void calculateRewards(User user) {
		List<VisitedLocationBean> userLocationBeanList = new ArrayList<>(user.getVisitedLocations());
		List<AttractionBean>          attractionBeanList   = gpsUtilProxy.getAttractions();

		for(VisitedLocationBean visitedLocation : userLocationBeanList) {
			for(AttractionBean attractionBean : attractionBeanList) {
				if(user.getUserRewardList().stream().noneMatch(r -> r.attractionBean.getAttractionName().equals(attractionBean.getAttractionName()))) {
					if(nearAttraction(visitedLocation, attractionBean)) {
						user.addUserReward(new UserReward(visitedLocation, attractionBean, getRewardPoints(attractionBean, user)));
					}
				}
			}
		}
	}
	
	public boolean isWithinAttractionProximity(AttractionBean attractionBean, LocationBean locationBean) {
		return (getDistance(attractionBean, locationBean) < attractionProximityRange);
	}
	
	private boolean nearAttraction(VisitedLocationBean visitedLocationBean, AttractionBean attractionBean) {
		return (getDistance(attractionBean, visitedLocationBean.getLocation()) < proximityBuffer);
	}
	
	public int getRewardPoints(AttractionBean attractionBean, User user) {
		return rewardsProxy.getAttractionRewardPoints(attractionBean.getAttractionId(), user.getUserId());
	}
	
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
