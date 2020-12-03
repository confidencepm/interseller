package za.co.dinoko.assignment.confidencemukwindidza.api.dto;

public class RouteResponse {

    String shortestPath;
    String errorMessage;

    // Setting the shortest path response message to the given destination
    public void setShortestPath(String shortestPath) {
        this.shortestPath = shortestPath;
    }

    // Setting error message to finding the shortest path to the given destination
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getShortestPath() {
        return shortestPath;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
