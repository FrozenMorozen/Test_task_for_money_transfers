package hello.services.impl;

import hello.dao.PointRepository;
import hello.entities.Point;
import hello.services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointServiceImpl implements PointService{

    @Autowired
    PointRepository pointRepository;

    @Override
    public Iterable<Point> findByNameContainingIgnoreCase(String name) {
        return pointRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public void add(Point point) {
        pointRepository.save(point);
    }
}
