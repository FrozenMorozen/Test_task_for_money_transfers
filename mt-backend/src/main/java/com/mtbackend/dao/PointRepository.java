package com.mtbackend.dao;

import com.mtbackend.entities.City;
import com.mtbackend.entities.Point;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PointRepository extends CrudRepository<Point, Long> {

    public Iterable<Point> findByNameContainingIgnoreCase(String name);

    public Iterable<Point> findByCity(City city);

    @Query(value = "select point.* from country cnt, city, point where city.country_id = cnt.id and point.city_id = city.id and (upper(cnt.name) like upper(?1||'%') or upper(city.name) like upper(?1||'%'))", nativeQuery = true)
    public List<Point> findByCountryAndCityFilter(String filter);
}