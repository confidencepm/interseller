package za.co.dinoko.assignment.confidencemukwindidza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.dinoko.assignment.confidencemukwindidza.model.Routes;

/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2020-11-29
 * @email: <a href="mailto:thabo@anylytical.co.za">Anylytical Technologies</a>
 * <a href="mailto:tl.matjuda@gmail.com">Personal GMail</a>
 */

@Repository
public interface RouteRepository extends JpaRepository<Routes, Integer> {
}
