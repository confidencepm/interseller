package za.co.dinoko.assignment.confidencemukwindidza.file;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.dinoko.assignment.confidencemukwindidza.model.Planet;
import za.co.dinoko.assignment.confidencemukwindidza.model.Routes;

import javax.persistence.Table;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SupportDataFileTest {


    @Autowired
    private SupportDataFile supportDataFile;

    private List<Planet> planetList;
    private List<Routes> routesList;


    @Test
    public void testThatWeCanReadFileIntoList() throws Exception {
        planetList = supportDataFile.getPlanetList();
        assertNotNull( planetList);
        assertTrue( planetList.size() > 0);
    }

    @Test
    public void testThat_J_IsFor_Mercury() throws Exception {
        planetList = supportDataFile.getPlanetList();
        Planet mercury = findPlanetByNode("J");
        assertNotNull( mercury);
        assertEquals( "Mercury", mercury.getPlanetName());
    }

    @Test
    public void testThatWeCanReadRoutesInput() throws Exception {
        routesList = supportDataFile.getRoutesList();
        assertNotNull( routesList);
        assertTrue( routesList.size() > 0);
    }

    private Planet findPlanetByNode(String planetNodeArg) {
        return planetList.stream().filter( planet -> planet.getPlanetNode().equals(planetNodeArg)).findFirst().get();
    }
}
