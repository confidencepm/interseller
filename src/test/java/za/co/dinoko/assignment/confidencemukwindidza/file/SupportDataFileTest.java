package za.co.dinoko.assignment.confidencemukwindidza.file;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.dinoko.assignment.confidencemukwindidza.model.Planet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SupportDataFileTest {


    @Autowired
    private SupportDataFile supportDataFile;


    @Test
    public void testFileReading() {
        List<Planet> planetList = supportDataFile.getPlanetList();
        assertNotNull( planetList);
        assertTrue( planetList.size() > 0);
    }
}
