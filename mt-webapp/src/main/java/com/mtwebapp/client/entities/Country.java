package com.mtwebapp.client.entities;


/**
 *  Страна
 */
public class Country {

    private long id;

    private String name;

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

    @Override
    public String toString() {
        return name;
    }
}
