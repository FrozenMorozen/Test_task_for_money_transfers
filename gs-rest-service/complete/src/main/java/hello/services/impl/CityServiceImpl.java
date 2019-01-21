package hello.services.impl;

import hello.dao.CityRepository;
import hello.entities.City;
import hello.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Iterable<City> findCityByFilter(String nameFilter){
        return cityRepository.findByNameContainingIgnoreCase(nameFilter);
    }

    @Override
    public void add(City city) {
        cityRepository.save(city);
    }
}
