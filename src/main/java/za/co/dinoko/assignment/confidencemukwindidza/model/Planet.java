package za.co.dinoko.assignment.confidencemukwindidza.model;

import javax.persistence.*;

@Entity
@Table( name = "PLANET")
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column( name = "node")
    private String planetNode;

    @Column( name = "name")
    private String planetName;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
