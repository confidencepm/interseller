package za.co.dinoko.assignment.confidencemukwindidza;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.dinoko.assignment.confidencemukwindidza.constants.PlanetConstants;
import za.co.dinoko.assignment.confidencemukwindidza.constants.RoutesContants;
import za.co.dinoko.assignment.confidencemukwindidza.file.SupportDataFileProcessor;
import za.co.dinoko.assignment.confidencemukwindidza.model.Planet;
import za.co.dinoko.assignment.confidencemukwindidza.model.Routes;
import za.co.dinoko.assignment.confidencemukwindidza.repository.PlanetRepository;
import za.co.dinoko.assignment.confidencemukwindidza.repository.RouteRepository;
import za.co.dinoko.assignment.confidencemukwindidza.service.ShortestPath;
import za.co.dinoko.assignment.confidencemukwindidza.service.ShortestPathService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestAlgorithmHere {

    @Autowired
    private SupportDataFileProcessor supportDataFileProcessor;

    @Autowired
    private ShortestPathService shortestPathService;

    @Autowired
    private ShortestPath shortestPath;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private RouteRepository routeRepository;

    private SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

    private static final Logger log = LoggerFactory.getLogger(TestAlgorithmHere.class);


    @Test
    public void testThatICanFindTheShortedRouteFromEARTH() throws Exception {
        Optional<Planet> earth = planetRepository.findById("A");
        assertTrue( earth.isPresent());

        Planet earthRecord = earth.get();
        assertEquals(PlanetConstants.EARTH, earthRecord.getPlanetName());


        planetRepository.findAll().stream()
                .forEach(planet -> graph.addVertex(planet.getPlanetNode()));
        assertTrue(graph.containsVertex("L'"));

        DefaultWeightedEdge edge = null;

        for (Routes routes : routeRepository.findAll()) {
            if (!routes.getPlanetOrigin().getPlanetNode().equals(routes.getPlanetDestination().getPlanetNode())) {
                // FIXME: Null point exception being thrown here needs to be fixed (See fixme below)
                edge = graph.addEdge(routes.getPlanetOrigin().getPlanetNode(), routes.getPlanetDestination().getPlanetNode());
            }
            addEdgeWeight(edge, routes.getDistanceInLightYears());
            assertTrue(graph.containsEdge(edge));
        }
        assertTrue(shortestPathService.shortestPathSearch("KKKKKK").contentEquals(RoutesContants.DESTINATION_NOT_FOUND));
        assertTrue(shortestPathService.shortestPathSearch(RoutesContants.ORIGIN).contentEquals(RoutesContants.DESTINATION_EQUAL_TO_ORIGIN));
        assertTrue(shortestPathService.shortestPathSearch("D'").contentEquals("[(A : C), (C : F), (F : J), (J : R), (R : P), (P : U), (U : K'), (K' : W), (W : C'), (C' : D')]"));
        assertFalse(shortestPathService.shortestPathSearch("D'").contentEquals("[(A : C), (C : F), (F : J), (J : R), (R : P), (K' : W), (W : C'), (C' : D')]"));

    }

    // Adding distance between origin and destination
    private void addEdgeWeight(DefaultWeightedEdge edge, double weight) {
        this.graph.setEdgeWeight(edge, weight);
    }

//    public String shortestPathSearch(String destination) {
//        final String origin = planetRepository.findById("A").toString();
//        String result = DijkstraShortestPath.findPathBetween(graph, origin, destination).toString();
//        System.out.println(result);
//        return result;
//    }

    public String findShortestPath(String source, String destination) {
        source = "A";
        log.info(DijkstraShortestPath.findPathBetween(this.graph, source,destination).toString());
        return DijkstraShortestPath.findPathBetween(this.graph, source,destination).toString();
    }
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
    // TODO: Adding validations on shortest path and the origin
    // TODO: Default origin to Node A (Planet Earth)
    // TODO: Add tests for finding shortest distance
    // FIXME: Find out the solution for the missing Planet (L') and implement. Option 1: will be to add any random planet name and Option 2: Ask for a way forward.
}
