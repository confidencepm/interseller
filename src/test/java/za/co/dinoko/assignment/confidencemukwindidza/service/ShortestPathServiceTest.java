package za.co.dinoko.assignment.confidencemukwindidza.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.dinoko.assignment.confidencemukwindidza.constants.RoutesConstants;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class ShortestPathServiceTest {

    @Autowired
    private ShortestPathService shortestPathService;

    @Test
    public void testThatNonExistingVertexCannotBeFound() {
        assertTrue(shortestPathService.shortestPathSearch("88")
                .contentEquals(RoutesConstants.DESTINATION_NOT_FOUND));
    }

    @Test
    public void testThatProvidedDestinationIsTheOrigin() {
        assertTrue(shortestPathService.shortestPathSearch(RoutesConstants.ORIGIN)
                .contentEquals(RoutesConstants.DESTINATION_EQUAL_TO_ORIGIN));
    }

    @Test
    public void testThatShortestPathFound() {
        assertTrue(shortestPathService.shortestPathSearch("K")
                .contentEquals("[(A : C), (C : F), (F : K)]"));
    }

    @Test
    public void testThatPathIsNotTheShortest() {
        assertFalse(shortestPathService.shortestPathSearch("D'")
                .contentEquals("[(A : C), (C : F), (F : J), (J : R), (R : P), (K' : W), (W : C'), (C' : D')]"));

    }

}
