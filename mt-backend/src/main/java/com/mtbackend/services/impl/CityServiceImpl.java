package com.mtbackend.services.impl;

import com.mtbackend.dao.CityRepository;
import com.mtbackend.entities.City;
import com.mtbackend.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Iterable<City> findCityByFilter(String nameFilter){
        return cityRepository.findByNameContainingIgnoreCase(nameFilter);
    }

    @Override
    public void add(City city) {
        cityRepository.save(city);
    }
}
