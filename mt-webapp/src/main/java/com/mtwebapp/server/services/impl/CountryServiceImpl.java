package com.mtwebapp.server.services.impl;


import com.mtwebapp.server.dao.CountryRepository;
import com.mtwebapp.server.entities.Country;
import com.mtwebapp.server.services.CountryService;
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
