package tourGuide.beans;

import java.util.Date;
import java.util.UUID;

public class VisitedLocationBean {

    private UUID         userId;
    private LocationBean locationBean;
    private Date         timeVisited;

    public VisitedLocationBean(UUID userId, LocationBean locationBean, Date timeVisited) {
        this.userId       = userId;
        this.locationBean = locationBean;
        this.timeVisited  = timeVisited;
    }

    public VisitedLocationBean() {

    }

    public UUID getUserId() {

        return userId;
    }

    public void setUserId(UUID userId) {

        this.userId = userId;
    }

    public LocationBean getLocation() {

        return locationBean;
    }

    public void setLocation(LocationBean locationBean) {

        this.locationBean = locationBean;
    }

    public Date getTimeVisited() {

        return timeVisited;
    }

    public void setTimeVisited(Date timeVisited) {

        this.timeVisited = timeVisited;
    }

    @Override
    public String toString() {

        return "VisitedLocationBean{" + "userId=" + userId + ", locationBean=" + locationBean + ", timeVisited=" + timeVisited + '}';
    }
}
