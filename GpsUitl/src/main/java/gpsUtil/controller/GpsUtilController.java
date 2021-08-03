package gpsUtil.controller;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gpsUtil.service.GpsUtilService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gpsUtil")
public class GpsUtilController {

   private final Logger logger = LoggerFactory.getLogger(GpsUtilController.class);

   @Autowired
   private GpsUtilService gpsUtilService;

    @GetMapping("/getUserLocation/{userId}")
    public VisitedLocation getUserLocation(@PathVariable UUID userId) {

        logger.info("Returns User Location for user: ");
        return gpsUtilService.getUserLocation(userId);
    }

    @GetMapping("/getAttractions")
    public List<Attraction> getAttractions() {

        logger.info("Returns all Attractions");
        return gpsUtilService.getAttractions();
    }





}
