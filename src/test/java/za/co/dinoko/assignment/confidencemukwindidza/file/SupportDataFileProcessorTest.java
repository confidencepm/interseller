package za.co.dinoko.assignment.confidencemukwindidza.file;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.dinoko.assignment.confidencemukwindidza.model.Planet;
import za.co.dinoko.assignment.confidencemukwindidza.model.Routes;
import za.co.dinoko.assignment.confidencemukwindidza.repository.PlanetRepository;
import za.co.dinoko.assignment.confidencemukwindidza.repository.RouteRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SupportDataFileProcessorTest {


    @Autowired
    private SupportDataFileProcessor supportDataFileProcessor;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private RouteRepository routeRepository;

    private List<Planet> planetList;


    @Test
    public void testThatWeCanReadFileIntoList() throws Exception {
        planetList = supportDataFileProcessor.getPlanetList();
        assertNotNull( planetList);
        assertTrue( planetList.size() > 0);
    }

    @Test
    public void testThat_J_IsFor_Mercury() throws Exception {
        planetList = supportDataFileProcessor.getPlanetList();
        Planet mercury = supportDataFileProcessor.getPlanetByNodeFromList("J");
        assertNotNull( mercury);
        assertEquals( "Mercury", mercury.getPlanetName());
    }

    @Test
    public void testThatWeCanSaveAllData() throws Exception {
        List<Planet> allPlanets = planetRepository.findAll();
        assertNotNull( allPlanets);
        assertTrue( CollectionUtils.isNotEmpty( allPlanets));

        List<Routes> allRoutes = routeRepository.findAll();
        assertNotNull( allRoutes);
        assertTrue( CollectionUtils.isNotEmpty( allRoutes));
    }

}

