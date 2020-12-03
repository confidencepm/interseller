package za.co.dinoko.assignment.confidencemukwindidza.model;

import javax.persistence.*;

@Entity
@Table(name = "ROUTES")
public class Routes {

    @Id
    @Column(name = "route_id")
    private Integer routeId;

    @ManyToOne
    @JoinColumn(name = "planet_origin", referencedColumnName = "node")
    private Planet planetOrigin;

    @ManyToOne
    @JoinColumn(name = "planet_destination", referencedColumnName = "node")
    private Planet planetDestination;

    @Column(name = "distance_in_light_years", unique = true, nullable = false)
    private Double distanceInLightYears;


    // Getters and Setters
    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Planet getPlanetOrigin() {
        return planetOrigin;
    }

    public void setPlanetOrigin(Planet planetOrigin) {
        this.planetOrigin = planetOrigin;
    }

    public Planet getPlanetDestination() {
        return planetDestination;
    }

    public void setPlanetDestination(Planet planetDestination) {
        this.planetDestination = planetDestination;
    }

    public Double getDistanceInLightYears() {
        return distanceInLightYears;
    }

    public void setDistanceInLightYears(Double distanceInLightYears) {
        this.distanceInLightYears = distanceInLightYears;
    }
}
