package com.mtbackend.services;

import com.mtbackend.entities.City;
import com.mtbackend.entities.Country;
import org.springframework.stereotype.Service;


public interface CityService {

    Iterable<City> findCityByFilter(String nameFilter);

    Iterable<City> findCitiesByCountry(Country country);

    Iterable<City> findByName(String name);

    void add(City city);

    void clear();
}
