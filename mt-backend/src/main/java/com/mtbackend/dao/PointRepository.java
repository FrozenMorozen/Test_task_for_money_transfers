package com.mtbackend.dao;

import com.mtbackend.entities.City;
import com.mtbackend.entities.Point;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PointRepository extends CrudRepository<Point, Long> {

    public Iterable<Point> findByNameContainingIgnoreCase(String name);

    public Iterable<Point> findByCity(City city);

    //public Iterable<Point> findAll();
}