package com.mtbackend.services.impl;


import com.mtbackend.entities.Country;
import com.mtbackend.services.CountryService;
import com.mtbackend.dao.CountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Iterable<Country> findCountryByFilter(String nameFilter){
        return countryRepository.findByNameContainingIgnoreCase(nameFilter);
    }

    @Override
    public void add(Country country) {
        countryRepository.save(country);
    }

    @Override
    public void deleteByName(String name) {
        for (Country country: countryRepository.findByName(name)) {
            countryRepository.delete(country);
        }
    }

    @Override
    public Country getCountryByName(String name) {
        for (Country country: countryRepository.findByName(name)) {
            return country;
        }
        return null;
    }

}
