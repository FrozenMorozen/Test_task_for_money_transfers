package com.mtbackend.entities;


import javax.persistence.*;
import java.util.List;


@Entity
public class Country {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToMany
    private List<City> cities;

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
