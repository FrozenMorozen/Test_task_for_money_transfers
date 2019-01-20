package com.mtbackend.entities;

import javax.persistence.*;

@Entity
public class City {
    @Id
    @GeneratedValue
    private Long id;

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
