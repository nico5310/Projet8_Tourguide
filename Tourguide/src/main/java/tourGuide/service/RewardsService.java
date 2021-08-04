package tourGuide.service;

import tourGuide.beans.AttractionBean;
import tourGuide.beans.LocationBean;
import tourGuide.beans.VisitedLocationBean;
import tourGuide.user.User;

public interface RewardsService {

    void calculateRewards(User user);

    boolean isWithinAttractionProximity(AttractionBean attractionBean, LocationBean locationBean);

    boolean nearAttraction(VisitedLocationBean visitedLocationBean, AttractionBean attractionBean);

    int getRewardPoints(AttractionBean attractionBean, User user);

    Double getDistance(LocationBean loc1, LocationBean loc2);


}
