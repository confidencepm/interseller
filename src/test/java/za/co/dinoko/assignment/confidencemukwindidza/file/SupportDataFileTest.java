package za.co.dinoko.assignment.confidencemukwindidza.file;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.dinoko.assignment.confidencemukwindidza.model.Planet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SupportDataFileTest {


    @Autowired
    private SupportDataFile supportDataFile;

    private List<Planet> planetList;


    @Test
    public void testThatWeCanReadFileIntoList() throws Exception {
        planetList = supportDataFile.getPlanetList();
        assertNotNull( planetList);
        assertTrue( planetList.size() > 0);
    }

    @Test
    public void testThat_J_IsFor_Mercury() throws Exception {
        planetList = supportDataFile.getPlanetList();
        Planet mercury = supportDataFile.getPlanetByNodeFromList("J");
        assertNotNull( mercury);
        assertEquals( "Mercury", mercury.getPlanetName());
    }

}

