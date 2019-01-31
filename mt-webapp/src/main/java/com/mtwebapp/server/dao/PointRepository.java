package com.mtwebapp.server.dao;

import com.mtwebapp.server.entities.City;
import com.mtwebapp.server.entities.Point;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PointRepository extends CrudRepository<Point, Long> {

    public List<Point> findByNameContainingIgnoreCase(String name);
    public List<Point> findByCity(City city);
}