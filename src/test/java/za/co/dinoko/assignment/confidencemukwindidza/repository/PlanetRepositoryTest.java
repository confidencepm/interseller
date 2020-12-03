package za.co.dinoko.assignment.confidencemukwindidza.repository;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import za.co.dinoko.assignment.confidencemukwindidza.file.SupportDataFileProcessor;
import za.co.dinoko.assignment.confidencemukwindidza.model.Planet;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PlanetRepositoryTest {

    @Autowired
    private SupportDataFileProcessor supportDataFileProcessor;

    @Autowired
    private PlanetRepository planetRepository;


    List<Planet> planetList;


    @BeforeTestClass
    public void setUp() {
        assertNotNull(supportDataFileProcessor);
        planetList = supportDataFileProcessor.getPlanetList();
    }

    @Test
    public void testThatWeCanPersistPlanets() {
        planetList = supportDataFileProcessor.getPlanetList();
        List<Planet> planets = planetRepository.saveAll(planetList);
        assertTrue(CollectionUtils.isNotEmpty(planets));

        List<Planet> allPlanets = planetRepository.findAll();
        assertTrue(CollectionUtils.isNotEmpty(allPlanets));
    }

    @Test
    public void testThatWeCanFindPlanetById() {
        planetList = supportDataFileProcessor.getPlanetList();
        planetRepository.saveAll(planetList);

        Optional<Planet> earthRecord = planetRepository.findById("A");
        assertTrue(earthRecord.isPresent());
        Planet earth = earthRecord.get();
        assertEquals("Earth", earth.getPlanetName());
    }
}
