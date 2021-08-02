package gpsUtil.service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class GpsUtilServiceImpl implements GpsUtilService {

    private static final Logger logger = LoggerFactory.getLogger(GpsUtilServiceImpl.class);

    @Autowired
    private static GpsUtil gpsUtil;


    @Override
    public VisitedLocation getUserLocation(UUID userId) {

        return gpsUtil.getUserLocation(userId);
    }


    @Override
    public List<Attraction> getAttractions() {

        return gpsUtil.getAttractions();
    }




}
