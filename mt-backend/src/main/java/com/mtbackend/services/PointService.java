package com.mtbackend.services;

import com.mtbackend.entities.City;
import com.mtbackend.entities.Point;

import java.util.List;


public interface PointService {

    Iterable<Point> findByNameContainingIgnoreCase(String name);

    void add(Point point);

    Iterable<Point> findByCity(City city);

    Iterable<Point> findAll();
}
