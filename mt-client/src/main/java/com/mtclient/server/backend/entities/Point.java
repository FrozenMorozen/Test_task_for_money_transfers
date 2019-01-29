package com.mtclient.server.backend.entities;


import com.mtclient.server.backend.entities.pointfields.PointAbility;
import com.mtclient.server.backend.entities.pointfields.PointType;

public class Point implements Comparable {
    public String name;
    public PointType pointType;
    public PointAbility pointAbility;
    public String address;
    public City city;

    public int compareTo(Object o) {
        Point tmp = (Point)o;
        int countryOrder = city.country.name.hashCode() - tmp.city.country.name.hashCode();

        if (countryOrder == 0) {
            int cityOrder = city.name.hashCode()-tmp.city.name.hashCode();

            if (cityOrder == 0) {
                return pointType.hashCode() - tmp.pointType.hashCode();
            } else {
                return cityOrder;
            }
        } else {
            return countryOrder;
        }
    }
}
