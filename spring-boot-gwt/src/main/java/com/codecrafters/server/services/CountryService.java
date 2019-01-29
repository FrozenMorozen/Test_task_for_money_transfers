package com.codecrafters.server.services;


import com.codecrafters.server.entities.Country;

public interface CountryService {

    Iterable<Country> findCountryByFilter(String nameFilter);

    void add(Country country);

    void  deleteByName(String name);

    Iterable<Country> getCountryByName(String name);

    void clear();
}
