package com.tourguide.gpsUtil.service;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import gpsUtil.service.GpsUtilServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ContextConfiguration(classes = {GpsUtilServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class GpsUtilServiceTest {

    @Autowired
    private GpsUtilServiceImpl gpsUtilServiceImpl;

    @Test
    @DisplayName("getUserLocationTest")
    public void getUserLocationTest() throws Exception {
        //GIVEN
        Locale.setDefault(Locale.US);
        double longitude = ThreadLocalRandom.current().nextDouble(-180.0D, 180.0D);
        longitude = Double.parseDouble(String.format("%.6f", longitude));
        double latitude = ThreadLocalRandom.current().nextDouble(-85.05112878D, 85.05112878D);
        latitude = Double.parseDouble(String.format("%.6f", latitude));
        UUID userId = UUID.randomUUID();
        VisitedLocation visitedLocation = new VisitedLocation(userId, new Location(latitude, longitude), new Date());
        //WHEN

        // THEN
        assertEquals(gpsUtilServiceImpl.getUserLocation(userId).userId, visitedLocation.userId);

    }

    @Test
    @DisplayName("getAttractionsTest")
    public void getAttractionsTest() throws Exception {
        //GIVEN
        List<Attraction> attractionList = gpsUtilServiceImpl.getAttractions();
        //WHEN

        // THEN
        assertEquals(26, attractionList.size());

    }

}
