package tourGuide.controller;

import com.jsoniter.output.JsonStream;
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

	@Autowired
    private TourGuideService tourGuideService;
	
    @RequestMapping("/")
    public String index() {
        return "Greetings from TourGuide!";
    }
    
    @RequestMapping("/getLocation")
    public VisitedLocationBean getLocation(@RequestParam String userName) {

        return tourGuideService.getUserLocation(userName);
    }

    @RequestMapping("/getNearbyAttractions")
    public List<NearByAttractionDto> getNearbyAttractions(@RequestParam String userName) {

        User user = tourGuideService.getUser(userName);
        VisitedLocationBean visitedLocationBean = tourGuideService.getUserLocation(userName);
        return tourGuideService.getNearByAttractions(visitedLocationBean, user);
    }
    
    @RequestMapping("/getRewards")
    public List<UserReward> getRewards(@RequestParam String userName) {

    	return tourGuideService.getUserRewards(tourGuideService.getUser(userName));
    }
    
    @RequestMapping("/getAllCurrentLocations")
    public List<AllUsersCurrentLocations> getAllCurrentLocations() {

        return tourGuideService.getAllCurrentLocations();
    }
    
    @RequestMapping("/getTripDeals")
    public String getTripDeals(@RequestParam String userName) {
    	List<ProviderBean> providerBeanList = tourGuideService.getTripDeals(tourGuideService.getUser(userName));
    	return JsonStream.serialize(providerBeanList);
    }


}