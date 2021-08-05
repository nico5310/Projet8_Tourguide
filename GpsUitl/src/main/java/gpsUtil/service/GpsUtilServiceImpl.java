package gpsUtil.service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GpsUtilServiceImpl implements GpsUtilService {

    private final Logger logger = LoggerFactory.getLogger(GpsUtilServiceImpl.class);

    private final GpsUtil gpsUtil = new GpsUtil();

    @Override
    public VisitedLocation getUserLocation(UUID userId) {

        logger.info("Returns User Location for user: ");
        return gpsUtil.getUserLocation(userId);
    }

    @Override
    public List<Attraction> getAttractions() {

        logger.info("Returns all Attractions");
        return gpsUtil.getAttractions();
    }


}
