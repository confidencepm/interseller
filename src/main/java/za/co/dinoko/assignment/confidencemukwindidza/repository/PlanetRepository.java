package za.co.dinoko.assignment.confidencemukwindidza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.dinoko.assignment.confidencemukwindidza.model.Planet;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, String> {
}
