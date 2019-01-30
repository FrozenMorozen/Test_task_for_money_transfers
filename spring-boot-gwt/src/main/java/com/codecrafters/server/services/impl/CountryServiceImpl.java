package com.codecrafters.server.services.impl;


import com.codecrafters.server.dao.CountryRepository;
import com.codecrafters.server.entities.Country;
import com.codecrafters.server.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Country> findCountryByFilter(String nameFilter){
        return countryRepository.findByNameContainingIgnoreCase(nameFilter);
    }

    @Override
    public void add(Country country) {
        countryRepository.save(country);
    }

    @Override
    public void clear() {
        countryRepository.deleteAll();
    }
}
