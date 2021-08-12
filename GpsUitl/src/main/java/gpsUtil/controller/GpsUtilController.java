package gpsUtil.controller;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import gpsUtil.service.GpsUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gpsUtil")
public class GpsUtilController {

   @Autowired
   private GpsUtilService gpsUtilService;

    @GetMapping("/getUserLocation/{userId}")
    public VisitedLocation getUserLocation(@PathVariable ("userId") UUID userId) {

        return gpsUtilService.getUserLocation(userId);
    }

    @GetMapping(value = "/getAttractions")
    public List<Attraction> getAttractions() {

        return gpsUtilService.getAttractions();
    }





}
