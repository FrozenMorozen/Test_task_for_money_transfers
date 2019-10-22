package com.mtwebapp.server.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Getter
@ToString
@EqualsAndHashCode
public class City {
    @Id
    @GeneratedValue
    private long id;

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
}
