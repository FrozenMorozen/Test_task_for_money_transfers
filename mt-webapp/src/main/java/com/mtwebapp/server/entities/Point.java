package com.mtwebapp.server.entities;


import com.mtwebapp.server.entities.pointfields.PointAbility;
import com.mtwebapp.server.entities.pointfields.PointType;

import javax.persistence.*;


@Entity
public class Point{

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (id != point.id) return false;
        if (name != null ? !name.equals(point.name) : point.name != null) return false;
        if (pointType != point.pointType) return false;
        if (pointAbility != point.pointAbility) return false;
        if (address != null ? !address.equals(point.address) : point.address != null) return false;
        return city.equals(point.city);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pointType != null ? pointType.hashCode() : 0);
        result = 31 * result + (pointAbility != null ? pointAbility.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + city.hashCode();
        return result;
    }
}
