package tripPricer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tripPricer.Provider;
import tripPricer.TripPricer;

import java.util.List;
import java.util.UUID;

@Service
public class TripPricerServiceImpl implements TripPricerService {

    private static final Logger logger = LoggerFactory.getLogger(TripPricerServiceImpl.class);

    @Autowired
    private static TripPricer tripPricer;

    @Override
    public List<Provider> getPrice(String apiKey, UUID attractionId, int adults, int children, int nightsStay, int rewardsPoints) {

        return tripPricer.getPrice(apiKey, attractionId, adults, children, nightsStay, rewardsPoints);
    }

}
