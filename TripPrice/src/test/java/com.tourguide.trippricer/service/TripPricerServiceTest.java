package com.tourguide.trippricer.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tripPricer.Provider;
import tripPricer.service.TripPricerServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@ContextConfiguration(classes = {TripPricerServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class TripPricerServiceTest {

    @Mock
    private TripPricerServiceImpl tripPricerServiceImpl;

    @Test
    @DisplayName("getPriceTest")
    public void getPriceTest() throws Exception {

        List<Provider> providers = new ArrayList<>();
        providers.add(new Provider(UUID.randomUUID(), "Holiday Travels", 1.00));
        providers.add(new Provider(UUID.randomUUID(), "Enterprise Ventures Limited", 2.00));
        providers.add(new Provider(UUID.randomUUID(), "Sunny Days", 3.00));
        providers.add(new Provider(UUID.randomUUID(), "FlyAway Trips", 4.00));
        providers.add(new Provider(UUID.randomUUID(), "United Partners Vacations", 5.00));
        providers.add(new Provider(UUID.randomUUID(), "Dream Trips", 6.00));
        providers.add(new Provider(UUID.randomUUID(), "Live Free", 7.00));
        providers.add(new Provider(UUID.randomUUID(), "Dancing Waves Cruselines and Partners", 8.00));
        providers.add(new Provider(UUID.randomUUID(), "AdventureCo", 9.00));
        providers.add(new Provider(UUID.randomUUID(), "Cure-Your-Blues", 10.00));

        when(tripPricerServiceImpl.getPrice(anyString(), any(UUID.class), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(providers);

        List<Provider> providerList = tripPricerServiceImpl.getPrice("UUID.randomUUID()", UUID.randomUUID(), 1, 1, 1,1);

        assertEquals(10, providerList.size());

    }


}
