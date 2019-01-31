package com.mtwebapp.server.services.impl;

import com.mtwebapp.server.dao.CityRepository;
import com.mtwebapp.server.entities.City;
import com.mtwebapp.server.entities.Country;
import com.mtwebapp.server.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> findCityByFilter(String nameFilter){
        return cityRepository.findByNameContainingIgnoreCase(nameFilter);
    }

    @Override
    public List<City> findCitiesByCountry(Country country) {
        return cityRepository.findByCountry(country);
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
