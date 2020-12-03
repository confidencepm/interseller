package za.co.dinoko.assignment.confidencemukwindidza.api.dto;

public class RouteResponse {

    String shortestPath;
    String errorMessage;

    public String getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(String shortestPath) {
        this.shortestPath = shortestPath;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
