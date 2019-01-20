package com.mtbackend;

import com.mtbackend.entities.City;
import com.mtbackend.entities.Country;
import com.mtbackend.services.CityService;
import com.mtbackend.services.CountryService;
import com.mtbackend.services.impl.CityServiceImpl;
import com.mtbackend.services.impl.CountryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

    CountryServiceImpl countryService;

    private CityServiceImpl cityService;

    @GetMapping("/cities")
    public Iterable<City> getCitiesByName(@RequestParam(value="name", required=false, defaultValue="") String name){
        return cityService.findCityByFilter(name);
    }

    @GetMapping("/countries")
    public Iterable<Country> getCountriesByName(@RequestParam(value="name", required=false, defaultValue="") String name){
        return countryService.findCountryByFilter(name);
    }

    @GetMapping("/testgenerate")
    public void setRepository(){

        cityService.add(new City("New York"));
        cityService.add(new City("New Angeles"));

        countryService.add(new Country("Russia"));
        cityService.add(new City("Moscow",countryService.getCountryByName("Russia")));

        countryService.add(new Country("Belorussia"));
        countryService.add(new Country("USA"));
        countryService.add(new Country("UAE"));
    }

    @GetMapping("/testdelete")
    public void setRepository(@RequestParam(value="name", required=false, defaultValue="") String name){
        countryService.deleteByName(name);
    }
}
