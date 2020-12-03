package za.co.dinoko.assignment.confidencemukwindidza.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "PLANET")
public class Planet implements Serializable {

    @Id
    @Column(name = "node", unique = true, nullable = false)
    private String planetNode;

    @Column(name = "name")
    private String planetName;


    public String getPlanetNode() {
        return planetNode;
    }

    public void setPlanetNode(String planetNode) {
        this.planetNode = planetNode;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }
}
