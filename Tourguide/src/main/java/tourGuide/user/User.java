package tourGuide.user;

import tourGuide.beans.ProviderBean;
import tourGuide.beans.VisitedLocationBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class User {
	private final UUID                      userId;
	private final String                    userName;
	private       String                    phoneNumber;
	private       String                    emailAddress;
	private       Date                      latestLocationTimestamp;
	private final List<VisitedLocationBean> visitedLocationBeanList = new ArrayList<>();
	private final List<UserReward>          userRewardList          = new ArrayList<>();
	private       UserPreferences           userPreferences         = new UserPreferences();
	private       List<ProviderBean>        tripDealsBeans          = new ArrayList<>();


	public User(UUID userId, String userName, String phoneNumber, String emailAddress) {
		this.userId = userId;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}
	
	public UUID getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setLatestLocationTimestamp(Date latestLocationTimestamp) {
		this.latestLocationTimestamp = latestLocationTimestamp;
	}
	
	public Date getLatestLocationTimestamp() {
		return latestLocationTimestamp;
	}


	//Method

	public void addToVisitedLocations(VisitedLocationBean visitedLocationBean) {
		visitedLocationBeanList.add(visitedLocationBean);
	}
	
	public List<VisitedLocationBean> getVisitedLocations() {
		return visitedLocationBeanList;
	}
	
	public void clearVisitedLocations() {
		visitedLocationBeanList.clear();
	}


	public void addUserReward(UserReward userReward) {
		if (userRewardList.stream().noneMatch(r -> r.attractionBean.getAttractionName().equals(userReward.attractionBean.getAttractionName()))) {
			userRewardList.add(userReward);
		}
	}
	
	public List<UserReward> getUserRewardList() {
		return userRewardList;
	}
	
	public UserPreferences getUserPreferences() {
		return userPreferences;
	}
	
	public void setUserPreferences(UserPreferences userPreferences) {
		this.userPreferences = userPreferences;
	}

	public VisitedLocationBean getLastVisitedLocation() {
		return visitedLocationBeanList.get(visitedLocationBeanList.size() - 1);
	}
	
	public void setTripDealsBeans(List<ProviderBean> tripDealsBeans) {
		this.tripDealsBeans = tripDealsBeans;
	}
	
	public List<ProviderBean> getTripDealsBeans() {
		return tripDealsBeans;
	}

}
