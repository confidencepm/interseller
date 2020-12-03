package za.co.dinoko.assignment.confidencemukwindidza.service;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.dinoko.assignment.confidencemukwindidza.constants.RoutesConstants;
import za.co.dinoko.assignment.confidencemukwindidza.model.Routes;
import za.co.dinoko.assignment.confidencemukwindidza.repository.PlanetRepository;
import za.co.dinoko.assignment.confidencemukwindidza.repository.RouteRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ShortestPathService implements ShortestPath {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private RouteRepository routeRepository;

    private static final Logger log = LoggerFactory.getLogger(ShortestPathService.class);

    private SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

    // Initializing of the Graph
    @PostConstruct
    public void initGraph() {
        addVertices();
        addEdges();
    }

    // Finding the shortest path to a given destination from the origin (A - Earth)
    @Override
    public String shortestPathSearch(String destination) {

        String origin;
        String shortestPath;

        // Validation to check if destination has been provided
        if (destination == null || destination.length() == 0) {
            log.info(RoutesConstants.REQUEST_BODY_NOT_FOUND);
            return RoutesConstants.REQUEST_BODY_NOT_FOUND;
        }

        // Validation to check that the Graph has been initialized and properties are set
        if (graph.vertexSet().isEmpty()) {
            initGraph();
        }

        // Validating that given destination exists and that it is not the same as the origin
        if (graph.containsVertex(destination) && !destination.equals(RoutesConstants.ORIGIN)) {
            origin = RoutesConstants.ORIGIN;
            shortestPath = DijkstraShortestPath.findPathBetween(graph, origin, destination).toString();
            log.info("The shortest path found: " + shortestPath);
            return shortestPath;
        } else if (destination.equals(RoutesConstants.ORIGIN)) {
            log.info(RoutesConstants.DESTINATION_EQUAL_TO_ORIGIN);
            return RoutesConstants.DESTINATION_EQUAL_TO_ORIGIN;
        } else {
            log.info(RoutesConstants.DESTINATION_NOT_FOUND);
            return RoutesConstants.DESTINATION_NOT_FOUND;
        }
    }

    // Setting Graph vertices
    private void addVertices() {
        planetRepository.findAll().stream()
                .forEach(planet -> graph.addVertex(planet.getPlanetNode()));
    }

    // Setting Graph edged
    private void addEdges() {
        AtomicReference<DefaultWeightedEdge> edge = new AtomicReference<>();
        List<Routes> routeList = routeRepository.findAll();

        routeList.forEach(routeRecord -> {
            String originNode = routeRecord.getPlanetOrigin().getPlanetNode();
            String destinationNode = routeRecord.getPlanetDestination().getPlanetNode();
            // Validating that edge nodes are different
            if (!originNode.equals(destinationNode)) {
                edge.set(graph.addEdge(originNode, destinationNode));
            }
            // Adding edge weight
            addEdgeWeight(edge.get(), routeRecord.getDistanceInLightYears());
        });
    }

    // Setting Graph edge weight
    private void addEdgeWeight(DefaultWeightedEdge edge, double weight) {
        graph.setEdgeWeight(edge, weight);
    }
}
