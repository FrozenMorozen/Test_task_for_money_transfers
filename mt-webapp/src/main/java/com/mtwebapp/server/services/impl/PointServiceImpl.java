package com.mtwebapp.server.services.impl;

import com.mtwebapp.server.dao.PointRepository;
import com.mtwebapp.server.entities.City;
import com.mtwebapp.server.entities.Point;
import com.mtwebapp.server.services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PointServiceImpl implements PointService {

    @Autowired
    private PointRepository pointRepository;

    @Override
    public void add(Point point) {
        pointRepository.save(point);
    }

    @Override
    public void clear() {
        pointRepository.deleteAll();
    }

    @Override
    public List<Point> findByCountryAndCityFilter(String filter) {
        return pointRepository.findByCountryAndCityFilter(filter);
    }
}
