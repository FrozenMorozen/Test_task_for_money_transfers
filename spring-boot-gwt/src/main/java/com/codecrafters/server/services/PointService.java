package com.codecrafters.server.services;

import com.codecrafters.server.entities.City;
import com.codecrafters.server.entities.Point;

import java.util.List;


public interface PointService {

    List<Point> findPointByFilter(String name);

    void add(Point point);

    Iterable<Point> findByCity(City city);

    Iterable<Point> findAll();

    void clear();
}
