package hello.services;


import hello.entities.Country;

public interface CountryService {

    Iterable<Country> findCountryByFilter(String nameFilter);

    void add(Country country);

    void  deleteByName(String name);

    Country getCountryByName(String name);
}
