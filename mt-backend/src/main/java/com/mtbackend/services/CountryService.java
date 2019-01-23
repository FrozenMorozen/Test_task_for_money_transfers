package com.mtbackend.services;


import com.mtbackend.entities.Country;
import org.springframework.stereotype.Service;


public interface CountryService {

    Iterable<Country> findCountryByFilter(String nameFilter);

    void add(Country country);

    void  deleteByName(String name);

    Iterable<Country> getCountryByName(String name);

    void clear();
}
