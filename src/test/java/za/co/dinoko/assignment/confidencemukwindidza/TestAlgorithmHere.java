package za.co.dinoko.assignment.confidencemukwindidza;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.dinoko.assignment.confidencemukwindidza.file.SupportDataFileProcessor;
import za.co.dinoko.assignment.confidencemukwindidza.model.Planet;
import za.co.dinoko.assignment.confidencemukwindidza.repository.PlanetRepository;
import za.co.dinoko.assignment.confidencemukwindidza.repository.RouteRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


    @Test
    public void testThatICanFindTheShortedRouteFromEARTH() throws Exception {
        Optional<Planet> earth = planetRepository.findById("A");
        assertTrue( earth.isPresent());

        Planet earthRecord = earth.get();
        assertEquals("Earth", earthRecord.getPlanetName());
    }
}
