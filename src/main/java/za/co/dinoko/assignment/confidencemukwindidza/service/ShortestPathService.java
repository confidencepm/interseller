package za.co.dinoko.assignment.confidencemukwindidza.service;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.dinoko.assignment.confidencemukwindidza.constants.RoutesContants;
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

    @PostConstruct
    public void initGraph() {
        addVertices();
        addEdges();
    }

    @Override
    public String shortestPathSearch(String destination) {

        String origin;
        String shortestPath;

        if (destination == null || destination.length() == 0) {
            log.info(RoutesContants.REQUEST_BODY_NOT_FOUND);
            return RoutesContants.REQUEST_BODY_NOT_FOUND;
        }
        if (graph.vertexSet().isEmpty()) {
            initGraph();
        }

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

    private void addVertices() {
        planetRepository.findAll().stream()
                .forEach(planet -> graph.addVertex(planet.getPlanetNode()));
    }

    private void addEdges() {
        AtomicReference<DefaultWeightedEdge> edge = new AtomicReference<>();
        List<Routes> routeList = routeRepository.findAll();

        routeList.forEach(routeRecord -> {
            String originNode = routeRecord.getPlanetOrigin().getPlanetNode();
            String destinationNode = routeRecord.getPlanetDestination().getPlanetNode();

            if (!originNode.equals(destinationNode)) {
                edge.set(graph.addEdge(originNode, destinationNode));
            }
            addEdgeWeight(edge.get(), routeRecord.getDistanceInLightYears());
        });
    }

    private void addEdgeWeight(DefaultWeightedEdge edge, double weight) {
        graph.setEdgeWeight(edge, weight);
    }
}
