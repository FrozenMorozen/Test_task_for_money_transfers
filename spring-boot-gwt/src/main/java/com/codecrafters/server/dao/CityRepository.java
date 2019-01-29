package com.codecrafters.server.dao;


import com.codecrafters.server.entities.City;
import com.codecrafters.server.entities.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    public Iterable<City> findByNameContainingIgnoreCase(String name);

    public Iterable<City> findByCountry(Country country);

    public Iterable<City> findByName(String name);

}
