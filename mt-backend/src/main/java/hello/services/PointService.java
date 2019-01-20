package hello.services;

import hello.entities.Point;


public interface PointService {

    Iterable<Point> findByNameContainingIgnoreCase(String name);

    void add(Point point);
}
