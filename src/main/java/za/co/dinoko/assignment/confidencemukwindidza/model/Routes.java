package za.co.dinoko.assignment.confidencemukwindidza.model;

public class Routes {

    private int routeId;
    private String planetOrigin;
    private String planetDestination;
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
