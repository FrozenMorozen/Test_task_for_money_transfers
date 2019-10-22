package com.mtwebapp.server.entities;


import com.mtwebapp.server.entities.pointfields.PointAbility;
import com.mtwebapp.server.entities.pointfields.PointType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Getter
@ToString
@EqualsAndHashCode
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

}
