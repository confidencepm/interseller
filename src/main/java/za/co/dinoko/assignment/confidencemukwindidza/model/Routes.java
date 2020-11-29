package za.co.dinoko.assignment.confidencemukwindidza.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ROUTES")
public class Routes {

    @Column(name = "id")
    private int routeId;

    @Column(name = "planet_origin")
    private String planetOrigin;

    @Column(name = "planet_destination")
    private String planetDestination;

    @Column(name = "distance")
    private double distanceInLightYears;


    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getPlanetOrigin() {
        return planetOrigin;
    }

    public void setPlanetOrigin(String planetOrigin) {
        this.planetOrigin = planetOrigin;
    }

    public String getPlanetDestination() {
        return planetDestination;
    }

    public void setPlanetDestination(String planetDestination) {
        this.planetDestination = planetDestination;
    }

    public double getDistanceInLightYears() {
        return distanceInLightYears;
    }

    public void setDistanceInLightYears(double distanceInLightYears) {
        this.distanceInLightYears = distanceInLightYears;
    }
}
