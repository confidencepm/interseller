package za.co.dinoko.assignment.confidencemukwindidza.repository;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import za.co.dinoko.assignment.confidencemukwindidza.file.SupportDataFile;
import za.co.dinoko.assignment.confidencemukwindidza.model.Planet;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PlanetRepositoryTest {

    @Autowired
    private SupportDataFile supportDataFile;

    @Autowired
    private PlanetRepository planetRepository;


    List<Planet> planetList;


    @BeforeTestClass
    public void setUp() {
        assertNotNull( supportDataFile);
        planetList = supportDataFile.getPlanetList();
    }

    @Test
    public void testThatWeCanPersistPlanets() {
        planetList = supportDataFile.getPlanetList();
        List<Planet> planets = planetRepository.saveAll(planetList);
        assertTrue( CollectionUtils.isNotEmpty( planets));

        List<Planet> allPlanets = planetRepository.findAll();
        assertTrue( CollectionUtils.isNotEmpty( allPlanets));
    }

    @Test
    public void testThatWeCanFindPlanetById() {
        planetList = supportDataFile.getPlanetList();
        planetRepository.saveAll(planetList);

        Optional<Planet> earthRecord = planetRepository.findById("A");
        assertTrue( earthRecord.isPresent());
        Planet earth = earthRecord.get();
        assertEquals( "Earth", earth.getPlanetName());
    }
}
