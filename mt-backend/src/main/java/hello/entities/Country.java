package hello.entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Country {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    //@OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany
    private Set<City> cities = new HashSet<>();

    public Country() {}

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
