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

    List<AllUsersCurrentLocations> getAllCurrentLocations();

    List<ProviderBean> getTripDeals(User user, int tripDuration, int numberOfAdults, int numberOfChildren);


    List<User> getAllUsers();

    User getUser(String userName);

    void addUser(User user);



    VisitedLocationBean trackUserLocation(User user);





}
