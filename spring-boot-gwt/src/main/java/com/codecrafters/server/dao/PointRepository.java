package com.codecrafters.server.dao;


import com.codecrafters.server.entities.City;
import com.codecrafters.server.entities.Point;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends CrudRepository<Point, Long> {

    public List<Point> findByNameContainingIgnoreCase(String name);

    public Iterable<Point> findByCity(City city);

}