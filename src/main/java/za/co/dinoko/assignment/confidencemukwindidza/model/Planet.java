package za.co.dinoko.assignment.confidencemukwindidza.model;

import javax.persistence.*;

@Entity
@Table ( name = "PLANET")
public class Planet {

    @Id
    @Column( name = "node", unique = true, nullable = false)
    private String planetNode;

    @Column( name = "name")
    private String planetName;



    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public String getPlanetNode() {
        return planetNode;
    }

    public void setPlanetNode(String planetNode) {
        this.planetNode = planetNode;
    }
}
