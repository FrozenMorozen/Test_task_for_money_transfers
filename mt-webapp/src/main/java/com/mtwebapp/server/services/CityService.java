package com.mtwebapp.server.services;


import com.mtwebapp.server.entities.City;
import com.mtwebapp.server.entities.Country;

import java.util.List;

public interface CityService {

    List<City> findCityByFilter(String nameFilter);

    List<City> findCitiesByCountry(Country country);

    void add(City city);

    void clear();
}
