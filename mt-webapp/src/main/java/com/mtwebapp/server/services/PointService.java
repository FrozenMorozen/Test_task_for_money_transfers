package com.mtwebapp.server.services;

import com.mtwebapp.server.entities.City;
import com.mtwebapp.server.entities.Point;

import java.util.List;


public interface PointService {

    void add(Point point);

    void clear();

    List<Point> findByCountryAndCityFilter(String filter);
}
