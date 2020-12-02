package za.co.dinoko.assignment.confidencemukwindidza.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.dinoko.assignment.confidencemukwindidza.constants.RoutesContants;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class ShortestPathServiceTest {

    private static final Logger log = LoggerFactory.getLogger(ShortestPathServiceTest.class);

    @Autowired
    private ShortestPathService shortestPathService;

    @Test
    public void testThatICanFindTheShortedRouteFromEARTH() throws Exception {
        assertTrue(shortestPathService.shortestPathSearch("KKKKKK")
                .contentEquals(RoutesContants.DESTINATION_NOT_FOUND));

        assertTrue(shortestPathService.shortestPathSearch(RoutesContants.ORIGIN)
                .contentEquals(RoutesContants.DESTINATION_EQUAL_TO_ORIGIN));

        assertTrue(shortestPathService.shortestPathSearch("D'")
                .contentEquals("[(A : C), (C : F), (F : J), (J : R), (R : P), (P : U), (U : K'), (K' : W), (W : C'), (C' : D')]"));

        assertFalse(shortestPathService.shortestPathSearch("D'")
                .contentEquals("[(A : C), (C : F), (F : J), (J : R), (R : P), (K' : W), (W : C'), (C' : D')]"));

    }

}
