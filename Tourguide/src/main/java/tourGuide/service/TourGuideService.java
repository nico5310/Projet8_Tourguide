package tourGuide.service;

import tourGuide.beans.ProviderBean;
import tourGuide.beans.VisitedLocationBean;
import tourGuide.dto.AllUsersCurrentLocations;
import tourGuide.dto.NearByAttractionDto;
import tourGuide.user.User;
import tourGuide.user.UserReward;

import java.util.List;

public interface TourGuideService {

    VisitedLocationBean getUserLocation(String userName);

    List<NearByAttractionDto> getNearByAttractions(VisitedLocationBean visitedLocationBean, User user);


    List<UserReward> getUserRewards(User user);

    User getUser(String userName);


    List<AllUsersCurrentLocations> getAllCurrentLocations();

    List<User> getAllUsers();



    void addUser(User user);

    List<ProviderBean> getTripDeals(User user);

    VisitedLocationBean trackUserLocation(User user);





}
