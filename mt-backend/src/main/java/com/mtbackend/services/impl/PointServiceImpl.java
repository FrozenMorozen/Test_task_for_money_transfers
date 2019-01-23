package com.mtbackend.services.impl;

import com.mtbackend.dao.PointRepository;
import com.mtbackend.entities.City;
import com.mtbackend.entities.Country;
import com.mtbackend.entities.Point;
import com.mtbackend.services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointServiceImpl implements PointService{

    @Autowired
    PointRepository pointRepository;

    @Override
    public Iterable<Point> findPointByFilter(String name) {
        return pointRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public void add(Point point) {
        pointRepository.save(point);
    }

    @Override
    public Iterable<Point> findByCity(City city) {
        return pointRepository.findByCity(city);
    }

    @Override
    public Iterable<Point> findAll() {
        return  pointRepository.findAll();
    }

    @Override
    public void clear() {
        pointRepository.deleteAll();
    }


}
