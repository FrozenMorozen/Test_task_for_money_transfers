package com.mtwebapp.client.entities;

import com.mtwebapp.client.entities.pointfields.PointAbility;
import com.mtwebapp.client.entities.pointfields.PointType;


/**
 *  Пункт денежных переводов
 */
public class Point {

    private long id;
    private String name;
    private PointType pointType;
    private PointAbility pointAbility;
    private String address;
    private City city;

    public Point() {}

    public Point(final String name) {
        this.name = name;
    }

    public Point(String name, PointType pointType, PointAbility pointAbility, String address, City city) {
        this.pointType = pointType;
        this.name = name;
        this.pointAbility = pointAbility;
        this.address = address;
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public PointType getPointType() {
        return pointType;
    }

    public void setPointType(PointType pointType) {
        this.pointType = pointType;
    }

    public PointAbility getPointAbility() {
        return pointAbility;
    }

    public void setPointAbility(PointAbility pointAbility) {
        this.pointAbility = pointAbility;
    }

    @Override
    public String toString() {
        return city +
                ", " + pointType + " '" + name + '\'' +
                " (" + pointAbility + ")." + "\n" +
                " Address: '" + address + '\'';
    }
}
