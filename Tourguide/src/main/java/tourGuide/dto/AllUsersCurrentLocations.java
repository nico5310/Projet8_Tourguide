package tourGuide.dto;

import java.util.UUID;

public class AllUsersCurrentLocations {

    private UUID userId;
    private double longitude;
    private double latitude;

    public AllUsersCurrentLocations(UUID userId, double longitude, double latitude) {

        this.userId    = userId;
        this.longitude = longitude;
        this.latitude  = latitude;
    }

    public UUID getUserId() {

        return userId;
    }

    public void setUserId(UUID userId) {

        this.userId = userId;
    }

    public double getLongitude() {

        return longitude;
    }

    public void setLongitude(double longitude) {

        this.longitude = longitude;
    }

    public double getLatitude() {

        return latitude;
    }

    public void setLatitude(double latitude) {

        this.latitude = latitude;
    }
}
