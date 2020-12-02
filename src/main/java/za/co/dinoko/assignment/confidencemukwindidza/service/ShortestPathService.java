package za.co.dinoko.assignment.confidencemukwindidza.service;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.dinoko.assignment.confidencemukwindidza.constants.RoutesContants;
import za.co.dinoko.assignment.confidencemukwindidza.model.Routes;
import za.co.dinoko.assignment.confidencemukwindidza.repository.PlanetRepository;
import za.co.dinoko.assignment.confidencemukwindidza.repository.RouteRepository;

import javax.annotation.PostConstruct;

@Component
public class ShortestPathService implements ShortestPath {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private RouteRepository routeRepository;

    private static final Logger log = LoggerFactory.getLogger(ShortestPathService.class);

    private SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

    @PostConstruct
    public void initGraph() {
        addVertices();
        addEdges();
    }

    public void addVertices() {
        planetRepository.findAll().stream()
                .forEach(planet -> graph.addVertex(planet.getPlanetNode()));
    }

    public void addEdges() {
        DefaultWeightedEdge edge = null;

        for (Routes routes : routeRepository.findAll()) {
            if (!routes.getPlanetOrigin().getPlanetNode().equals(routes.getPlanetDestination().getPlanetNode())) {
                // FIXME: Null point exception being thrown here needs to be fixed (See fixme below)
                edge = graph.addEdge(routes.getPlanetOrigin().getPlanetNode(), routes.getPlanetDestination().getPlanetNode());
            }
            addEdgeWeight(edge, routes.getDistanceInLightYears());
        }
    }

    private void addEdgeWeight(DefaultWeightedEdge edge, double weight) {
        graph.setEdgeWeight(edge, weight);
    }

    @Override
    public String shortestPathSearch(String destination) {

        String origin;
        String shortestPath;

        if (graph.containsVertex(destination) && !destination.equals(RoutesContants.ORIGIN)) {
            origin = RoutesContants.ORIGIN;
            shortestPath = DijkstraShortestPath.findPathBetween(graph, origin, destination).toString();
            log.info("The shortest path found: " + shortestPath);
            return shortestPath;
        } else if (destination.equals(RoutesContants.ORIGIN)) {
            log.info(RoutesContants.DESTINATION_EQUAL_TO_ORIGIN);
            return RoutesContants.DESTINATION_EQUAL_TO_ORIGIN;
        } else {
            log.info(RoutesContants.DESTINATION_NOT_FOUND);
            return RoutesContants.DESTINATION_NOT_FOUND;
        }
    }
}
