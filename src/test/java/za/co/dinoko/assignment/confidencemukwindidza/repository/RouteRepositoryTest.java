package za.co.dinoko.assignment.confidencemukwindidza.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import za.co.dinoko.assignment.confidencemukwindidza.file.SupportDataFileProcessor;
import za.co.dinoko.assignment.confidencemukwindidza.model.Planet;
import za.co.dinoko.assignment.confidencemukwindidza.model.Routes;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RouteRepositoryTest {

    @Autowired
    private SupportDataFileProcessor supportDataFileProcessor;

    @Autowired
    private RouteRepository routeRepository;

    
    @BeforeTestClass
    public void setUp() {
        assertNotNull(supportDataFileProcessor);
    }


    @Test
    public void testThatWeCanPersistRoutesIntoDatabase() throws Exception {
        routeRepository.saveAll( supportDataFileProcessor.getRouteList());
        List<Routes> allRoutes = routeRepository.findAll();

        assertNotNull( allRoutes);
        assertTrue( allRoutes.size() > 0);
    }

    @Test
    public void testThatWeCanFindRouteByID() {
        routeRepository.saveAll( supportDataFileProcessor.getRouteList());
        Optional<Routes> foundRouteById = routeRepository.findById(20);

        assertTrue( foundRouteById.isPresent());
        Routes route = foundRouteById.get();
        assertNotNull( route);

        assertNotNull( route.getRouteId());
        assertNotNull( route.getPlanetOrigin());
        assertNotNull( route.getPlanetDestination());
        assertNotNull( route.getDistanceInLightYears());

        assertEquals( 20, route.getRouteId());

        Planet routeOrigin = route.getPlanetOrigin();
        assertNotNull( routeOrigin);
        assertNotNull( routeOrigin.getPlanetNode());
        assertNotNull( routeOrigin.getPlanetName());
        assertEquals("O", routeOrigin.getPlanetNode());

        Planet routeDestination = route.getPlanetDestination();
        assertNotNull( routeDestination);
        assertNotNull( routeDestination.getPlanetNode());
        assertNotNull( routeDestination.getPlanetName());
        assertEquals("U", routeDestination.getPlanetNode());

        assertEquals( 5.26, route.getDistanceInLightYears());
    }
}
