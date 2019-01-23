package com.mtbackend.services;

import com.mtbackend.entities.City;
import org.springframework.stereotype.Service;


public interface CityService {

    Iterable<City> findCityByFilter(String nameFilter);

    void add(City city);

    void clear();
}
