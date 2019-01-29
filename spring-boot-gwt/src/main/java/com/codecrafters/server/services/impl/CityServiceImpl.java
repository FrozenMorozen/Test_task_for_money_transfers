package com.codecrafters.server.services.impl;

import com.codecrafters.server.dao.CityRepository;
import com.codecrafters.server.entities.City;
import com.codecrafters.server.entities.Country;
import com.codecrafters.server.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Iterable<City> findCityByFilter(String nameFilter){
        return cityRepository.findByNameContainingIgnoreCase(nameFilter);
    }

    @Override
    public Iterable<City> findCitiesByCountry(Country country) {
        return cityRepository.findByCountry(country);
    }

    @Override
    public Iterable<City> findByName(String name) {
        return cityRepository.findByName(name);
    }

    @Override
    public void add(City city) {
        cityRepository.save(city);
    }

    @Override
    public void clear() {
        cityRepository.deleteAll();
    }
}
