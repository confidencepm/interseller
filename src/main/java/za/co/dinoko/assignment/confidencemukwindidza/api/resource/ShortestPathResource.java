package za.co.dinoko.assignment.confidencemukwindidza.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.dinoko.assignment.confidencemukwindidza.api.dto.RouteRequest;
import za.co.dinoko.assignment.confidencemukwindidza.api.dto.RouteResponse;
import za.co.dinoko.assignment.confidencemukwindidza.constants.RoutesConstants;
import za.co.dinoko.assignment.confidencemukwindidza.service.ShortestPath;

@RestController
@RequestMapping(path = "/shortest-path")
public class ShortestPathResource {

    @Autowired
    private ShortestPath shortestPath;

    @GetMapping
    @ResponseBody
    public ResponseEntity<RouteResponse> shortestPathToDestination(@RequestBody RouteRequest routeRequest) {

        RouteResponse routeResponse = new RouteResponse();
        String shortestPathText;

        // Validating the provided routeRequest
        if (routeRequest == null) {
            shortestPathText = RoutesConstants.REQUEST_BODY_NOT_FOUND;
        } else {
            shortestPathText = shortestPath.shortestPathSearch(routeRequest.getRouteRequest());
        }

        // Validating the given path and setting the relevant response
        if (shortestPathText.startsWith("[")) {
            routeResponse.setShortestPath(shortestPathText);
        } else {
            routeResponse.setErrorMessage(shortestPathText);
        }

        return ResponseEntity.ok(routeResponse);
    }
}
