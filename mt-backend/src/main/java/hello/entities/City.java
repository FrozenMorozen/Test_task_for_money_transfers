package hello.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class City {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Point> points;

    @Column
    private String name;

    @ManyToOne
    private Country country;

    public City() {}

    public City(String name) {
        this.name = name;
    }

    public City(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
