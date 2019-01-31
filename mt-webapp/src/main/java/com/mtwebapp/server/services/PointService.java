package com.mtwebapp.server.services;

import com.mtwebapp.server.entities.City;
import com.mtwebapp.server.entities.Point;

import java.util.List;


public interface PointService {

    List<Point> findPointByFilter(String name);

    void add(Point point);

    List<Point> findByCity(City city);

    Iterable<Point> findAll();

    void clear();
}
