package za.co.dinoko.assignment.confidencemukwindidza.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table ( name = "PLANET")
public class Planet implements Serializable {

    @Id
    @Column( name = "node", unique = true, nullable = false)
    private String planetNode;

    @Column( name = "name")
    private String planetName;

//    @OneToMany(mappedBy = "planetOrigin")
//    private List<Routes> routeOrigins;
//
//    @OneToMany(mappedBy = "planetDestination")
//    private List<Routes> routeDestinations;



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

//    public List<Routes> getRouteOrigins() {
//        return routeOrigins;
//    }
//
//    public void setRouteOrigins(List<Routes> routeOrigins) {
//        this.routeOrigins = routeOrigins;
//    }
//
//    public List<Routes> getRouteDestinations() {
//        return routeDestinations;
//    }
//
//    public void setRouteDestinations(List<Routes> routeDestinations) {
//        this.routeDestinations = routeDestinations;
//    }
}
