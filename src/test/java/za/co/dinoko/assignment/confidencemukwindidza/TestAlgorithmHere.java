package za.co.dinoko.assignment.confidencemukwindidza;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.dinoko.assignment.confidencemukwindidza.file.SupportDataFileProcessor;
import za.co.dinoko.assignment.confidencemukwindidza.model.Planet;
import za.co.dinoko.assignment.confidencemukwindidza.model.Routes;
import za.co.dinoko.assignment.confidencemukwindidza.repository.PlanetRepository;
import za.co.dinoko.assignment.confidencemukwindidza.repository.RouteRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2020-11-29
 * @email: <a href="mailto:thabo@anylytical.co.za">Anylytical Technologies</a>
 * <a href="mailto:tl.matjuda@gmail.com">Personal GMail</a>
 */

@SpringBootTest
public class TestAlgorithmHere {

    @Autowired
    private SupportDataFileProcessor supportDataFileProcessor;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private RouteRepository routeRepository;

    private SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);


    @Test
    public void testThatICanFindTheShortedRouteFromEARTH() throws Exception {
        Optional<Planet> earth = planetRepository.findById("A");
        assertTrue( earth.isPresent());

        Planet earthRecord = earth.get();
        assertEquals("Earth", earthRecord.getPlanetName());


        planetRepository.findAll().stream()
                .forEach(planet -> graph.addVertex(planet.getPlanetNode()));
        assertTrue(graph.containsVertex("K'"));

        DefaultWeightedEdge edge = null;

        for (Routes routes : routeRepository.findAll()) {
            if (!routes.getPlanetOrigin().getPlanetNode().equals(routes.getPlanetDestination().getPlanetNode())) {
                // FIXME: Null point exception being thrown here needs to be fixed (See fixme below)
                edge = graph.addEdge(routes.getPlanetOrigin().getPlanetNode(), routes.getPlanetDestination().getPlanetNode());
            }
            addEdgeWeight(edge, routes.getDistanceInLightYears());
            assertTrue(graph.containsEdge(edge));
        }

    }

    // Adding distance between origin and destination
    private void addEdgeWeight(DefaultWeightedEdge edge, double weight) {
        this.graph.setEdgeWeight(edge, weight);
    }

    public GraphPath shortestPath(String origin, String destination) {
        return (new DijkstraShortestPath(graph)).getPath(origin, destination);
    }

    // TODO: Move code into a class and create a service for the API resource to find shortest distance
    // TODO: Default origin to Node A (Planet Earth)
    // TODO: Add tests for finding shortest distance
    // FIXME: Find out the solution for the missing Planet (L') and implement. Option 1: will be to add any random planet name and Option 2: Ask for a way forward.
}
