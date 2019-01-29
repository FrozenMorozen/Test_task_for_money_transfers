package com.codecrafters.server.services.impl;


import com.codecrafters.server.dao.CountryRepository;
import com.codecrafters.server.entities.Country;
import com.codecrafters.server.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
    public Iterable<Country> getCountryByName(String name) {
        return countryRepository.findByName(name);
    }

    @Override
    public void clear() {
        countryRepository.deleteAll();
    }

}
