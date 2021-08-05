package tourGuide.service;

import tourGuide.beans.ProviderBean;
import tourGuide.beans.VisitedLocationBean;
import tourGuide.dto.NearByAttractionDto;
import tourGuide.user.User;
import tourGuide.user.UserReward;

import java.util.List;

public interface TourGuideService {


    List<UserReward> getUserRewards(User user);

    VisitedLocationBean getUserLocation(String userName);

    User getUser(String userName);

    List<User> getAllUsers();

    void addUser(User user);

    List<ProviderBean> getTripDeals(User user);

    VisitedLocationBean trackUserLocation(User user);

    List<NearByAttractionDto> getNearByAttractions(VisitedLocationBean visitedLocationBean, User user);



}
