package com.codecrafters.server.services;


import com.codecrafters.server.entities.Country;

import java.util.List;

public interface CountryService {

    List<Country> findCountryByFilter(String nameFilter);

    void add(Country country);

    void clear();
}
