package za.co.dinoko.assignment.confidencemukwindidza.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.dinoko.assignment.confidencemukwindidza.service.ShortestPath;

@RestController
public class ShortestPathResource {

    @Autowired
    private ShortestPath shortestPath;

    @RequestMapping(method = RequestMethod.GET, value = "/{destination}")
    @ResponseBody
    public String shortestPathToDestination(@PathVariable("destination") String destination) {
        return shortestPath.shortestPathSearch(destination);
    }
}
