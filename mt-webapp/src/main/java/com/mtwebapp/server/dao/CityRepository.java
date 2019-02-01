package com.mtwebapp.server.dao;

import com.mtwebapp.server.entities.City;
import com.mtwebapp.server.entities.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    List<City> findByNameContainingIgnoreCase(String name);
    List<City> findByCountry(Country country);
}
