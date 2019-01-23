package com.mtbackend.entities;

import com.mtbackend.entities.pointfields.PointAbility;
import com.mtbackend.entities.pointfields.PointType;
import javax.persistence.*;


/**
 *  Пункт денежных переводов
 */
@Entity
public class Point {

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
}
