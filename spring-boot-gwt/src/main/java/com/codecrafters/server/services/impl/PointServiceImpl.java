package com.codecrafters.server.services.impl;


import com.codecrafters.server.dao.CityRepository;
import com.codecrafters.server.dao.CountryRepository;
import com.codecrafters.server.dao.PointRepository;
import com.codecrafters.server.entities.City;
import com.codecrafters.server.entities.Country;
import com.codecrafters.server.entities.Point;
import com.codecrafters.server.services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PointServiceImpl implements PointService {

    @Autowired
    PointRepository pointRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CountryRepository countryRepository;

    @Override
    public List<Point> findPointByFilter(String name) {
        Set<Point> resultPoints = new HashSet<>();

        // Отфильтровать пункты по городам
        for (City city: cityRepository.findByName(name)) {
            resultPoints.addAll(pointRepository.findByCity(city));
        }

        // Добавить пункты с фильтром по стране
        for (Country country: countryRepository.findByName(name)) {
            for (City city: cityRepository.findByCountry(country)) {
                resultPoints.addAll(pointRepository.findByCity(city));
            }
        }

        List<Point> points = new ArrayList<>();
        points.addAll(resultPoints);
        return points;
    }

    @Override
    public void add(Point point) {
        pointRepository.save(point);
    }

    @Override
    public List<Point> findByCity(City city) {
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
