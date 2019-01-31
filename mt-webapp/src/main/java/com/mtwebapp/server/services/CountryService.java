package com.mtwebapp.server.services;


import com.mtwebapp.server.entities.Country;

import java.util.List;

public interface CountryService {

    List<Country> findCountryByFilter(String nameFilter);

    void add(Country country);

    void clear();
}
