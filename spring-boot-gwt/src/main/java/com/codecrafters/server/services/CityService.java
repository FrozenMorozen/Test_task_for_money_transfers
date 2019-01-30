package com.codecrafters.server.services;


import com.codecrafters.server.entities.City;
import com.codecrafters.server.entities.Country;

import java.util.List;

public interface CityService {

    List<City> findCityByFilter(String nameFilter);

    List<City> findCitiesByCountry(Country country);

    void add(City city);

    void clear();
}
