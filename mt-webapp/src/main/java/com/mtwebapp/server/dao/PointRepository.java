package com.mtwebapp.server.dao;

import com.mtwebapp.server.entities.City;
import com.mtwebapp.server.entities.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PointRepository extends CrudRepository<Point, Long> {

    @Query(value = "select point.* " +
                    "from country, city, point " +
                    "where  city.country_id = country.id " +
                            "and point.city_id = city.id " +
                            "and (upper(country.name) like upper(?1||'%') or upper(city.name) like upper(?1||'%'))" +
                    "order by country.id , city.id",
            nativeQuery = true)
    List<Point> findByCountryAndCityFilter(String filter);
}