package hello.services;

import hello.entities.City;


public interface CityService {

    Iterable<City> findCityByFilter(String nameFilter);

    void add(City city);
}
