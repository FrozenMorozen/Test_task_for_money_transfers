package com.mtwebapp.server.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Getter
@ToString
@EqualsAndHashCode
public class Country {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    public Country() {}

    public Country(String name) {
        this.name = name;
    }

}
