package com.codecrafters.server.services;


import com.codecrafters.server.entities.City;
import com.codecrafters.server.entities.Country;

public interface CityService {

    Iterable<City> findCityByFilter(String nameFilter);

    Iterable<City> findCitiesByCountry(Country country);

    Iterable<City> findByName(String name);

    void add(City city);

    void clear();
}
