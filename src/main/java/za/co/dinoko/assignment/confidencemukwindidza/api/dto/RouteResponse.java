package za.co.dinoko.assignment.confidencemukwindidza.api.dto;

/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2020-12-02
 * @email: <a href="mailto:thabo@anylytical.co.za">Anylytical Technologies</a>
 * <a href="mailto:tl.matjuda@gmail.com">Personal GMail</a>
 */

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
