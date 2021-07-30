package tourGuide.dto;

public class NearByAttractionDto {

    private String attractionName;
    private double attractionLongitude;
    private double attractionLatitude;
    private double userLongitude;
    private double userLatitude;
    private double distance;
    private int rewardPoints;

    public NearByAttractionDto(String attractionName, double attractionLongitude, double attractionLatitude, double userLongitude, double userLatitude, double distance, int rewardPoints) {

        this.attractionName      = attractionName;
        this.attractionLongitude = attractionLongitude;
        this.attractionLatitude  = attractionLatitude;
        this.userLongitude       = userLongitude;
        this.userLatitude        = userLatitude;
        this.distance            = distance;
        this.rewardPoints        = rewardPoints;
    }

    public NearByAttractionDto() {

    }

    public String getAttractionName() {

        return attractionName;
    }

    public void setAttractionName(String attractionName) {

        this.attractionName = attractionName;
    }

    public double getAttractionLongitude() {

        return attractionLongitude;
    }

    public void setAttractionLongitude(double attractionLongitude) {

        this.attractionLongitude = attractionLongitude;
    }

    public double getAttractionLatitude() {

        return attractionLatitude;
    }

    public void setAttractionLatitude(double attractionLatitude) {

        this.attractionLatitude = attractionLatitude;
    }

    public double getUserLongitude() {

        return userLongitude;
    }

    public void setUserLongitude(double userLongitude) {

        this.userLongitude = userLongitude;
    }

    public double getUserLatitude() {

        return userLatitude;
    }

    public void setUserLatitude(double userLatitude) {

        this.userLatitude = userLatitude;
    }

    public double getDistance() {

        return distance;
    }

    public void setDistance(double distance) {

        this.distance = distance;
    }

    public int getRewardPoints() {

        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {

        this.rewardPoints = rewardPoints;
    }
}
