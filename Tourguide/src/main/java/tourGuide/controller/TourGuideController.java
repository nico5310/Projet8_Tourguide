package tourGuide.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tourGuide.beans.ProviderBean;
import tourGuide.beans.VisitedLocationBean;
import tourGuide.dto.AllUsersCurrentLocations;
import tourGuide.dto.NearByAttractionDto;
import tourGuide.service.TourGuideService;
import tourGuide.user.User;
import tourGuide.user.UserReward;

import java.util.List;

@RestController
public class TourGuideController {

    private final Logger logger = LoggerFactory.getLogger(TourGuideController.class);

	@Autowired
    private TourGuideService tourGuideService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from TourGuide!";
    }

    @ApiOperation(value = "Get location by username of user")
    @RequestMapping("/getLocation")
    public VisitedLocationBean getLocation(@RequestParam String userName) {

        logger.info("getLocation for :" + userName);
        return tourGuideService.getUserLocation(userName);
    }

    @ApiOperation(value = "Shows the 5 closest attractions by username of user")
    @RequestMapping("/getNearbyAttractions")
    public List<NearByAttractionDto> getNearbyAttractions(@RequestParam String userName) {

        logger.info("getNearByAttractions for :" + userName);
        User user = tourGuideService.getUser(userName);
        VisitedLocationBean visitedLocationBean = tourGuideService.getUserLocation(userName);
        return tourGuideService.getNearByAttractions(visitedLocationBean, user);
    }

    @ApiOperation(value = "Shows the rewards by by username of user")
    @RequestMapping("/getRewards")
    public List<UserReward> getRewards(@RequestParam String userName) {

        logger.info("getRewards for :" + userName);
    	return tourGuideService.getUserRewards(tourGuideService.getUser(userName));
    }

    @ApiOperation(value = "Shows all current locations for all users")
    @RequestMapping("/getAllCurrentLocations")
    public List<AllUsersCurrentLocations> getAllCurrentLocations() {

        logger.info("getAllCurrentLocations");
        return tourGuideService.getAllCurrentLocations();
    }

    @ApiOperation(value = "Get trip deals by username for user with his preferences")
    @RequestMapping("/getTripDeals")
    public List<ProviderBean> getTripDeals(@RequestParam String userName, @RequestParam int tripDuration, @RequestParam int numberOfAdults, @RequestParam int numberOfChildren) {

        logger.info("getTripDeals for :" + userName + tripDuration + numberOfAdults + numberOfChildren);
    	List<ProviderBean> providerBeanList = tourGuideService.getTripDeals(tourGuideService.getUser(userName), tripDuration, numberOfAdults, numberOfChildren);
    	return providerBeanList;
    }


}