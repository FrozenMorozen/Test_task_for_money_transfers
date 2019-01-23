package com.mtclient.server.backend.entities;


import com.mtclient.server.backend.entities.pointfields.PointAbility;
import com.mtclient.server.backend.entities.pointfields.PointType;

public class Point {
    public String name;
    public PointType pointType;
    public PointAbility pointAbility;
    public String address;
    public City city;
}
