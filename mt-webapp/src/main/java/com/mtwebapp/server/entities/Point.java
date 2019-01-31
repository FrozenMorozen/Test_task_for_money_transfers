package com.mtwebapp.server.entities;


import com.mtwebapp.server.entities.pointfields.PointAbility;
import com.mtwebapp.server.entities.pointfields.PointType;

import javax.persistence.*;


/**
 *  Пункт денежных переводов
 */
@Entity
public class Point implements Comparable{

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private PointType pointType;

    @Column
    @Enumerated(EnumType.STRING)
    private PointAbility pointAbility;

    @Column
    private String address;

    @ManyToOne
    private City city;

    public Point() {
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
        return "Point{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pointType=" + pointType +
                ", pointAbility=" + pointAbility +
                ", address='" + address + '\'' +
                ", city=" + city +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Point tmp = (Point)o;
        int countryOrder = city.getCountry().getName().hashCode() - tmp.getCity().getCountry().getName().hashCode();

        if (countryOrder == 0) {
            int cityOrder = city.getName().hashCode()-tmp.getCity().getName().hashCode();

            if (cityOrder == 0) {
                return pointType.hashCode() - tmp.getPointType().hashCode();
            } else {
                return cityOrder;
            }
        } else {
            return countryOrder;
        }
    }
}
