package com.mtbackend;

import com.mtbackend.entities.City;
import com.mtbackend.entities.Country;
import com.mtbackend.entities.Point;
import com.mtbackend.entities.pointfields.PointAbility;
import com.mtbackend.entities.pointfields.PointType;
import com.mtbackend.services.impl.CityServiceImpl;
import com.mtbackend.services.impl.CountryServiceImpl;
import com.mtbackend.services.impl.PointServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class Controller {

    @Autowired
    private CityServiceImpl cityService;

    @Autowired
    private CountryServiceImpl countryService;

    @Autowired
    private PointServiceImpl pointService;

    @GetMapping("/cities")
    public Iterable<City> getCitiesByName(@RequestParam(value="name", required=false, defaultValue="") String name){
        return cityService.findCityByFilter(name);
    }

    @GetMapping("/countries")
    public Iterable<Country> getCountriesByName(@RequestParam(value="name", required=false, defaultValue="") String name){
        return countryService.findCountryByFilter(name);
    }

    @GetMapping("/points")
    public Iterable<Point> getPointByName(@RequestParam(value="name", required=false, defaultValue="") String name){
        return pointService.findPointByFilter(name);
    }

    @GetMapping("/pointscityname")
    public List<Point> getPointByCityName(@RequestParam(value="name", required=false, defaultValue="") String name){
        if (name.equals("")) {
            return (List<Point>) pointService.findAll();
        } else {
            Iterable<City> cities = cityService.findCityByFilter(name);

            List<Point> resultPoints = new ArrayList<>();
            for (City city: cities) {
                resultPoints.addAll((Collection<? extends Point>) pointService.findByCity(city));
            }
            return resultPoints;
        }
    }

    @GetMapping("/pointsfilter")
    public Iterable<Point> getPointByFilter(@RequestParam(value="name", required=false, defaultValue="") String filter){

        // Фильтр не задан - вернуть все пункты
        if (filter.equals("")) {
            return pointService.findAll();

        } else {
            Set<Point> resultPoints = new HashSet<>();

            // Отфильтровать пункты по городам
            for (City city: cityService.findCityByFilter(filter)) {
                resultPoints.addAll((Collection<? extends Point>) pointService.findByCity(city));
            }

            // Отфильтровать пункты по странам
            for (Country country: countryService.findCountryByFilter(filter)) {
                for (Point point: pointService.findAll()) {
                    if (point.getCity().getCountry().equals(country)) {
                        resultPoints.add(point);
                    }
                }
            }

            return resultPoints;
        }
    }

    @GetMapping("/testgenerate")
    public void generateTestData(){

        Country country = new Country("Russia");
        City city = new City("Moscow",country);

        countryService.add(country);
        cityService.add(city);
        pointService.add(new Point("Euroset", PointType.STORE, PointAbility.SENDING_AND_RECEIVING, "St. Pervomajskaja, 104/1", city));

        city = new City("Novosibirsk",country);

        cityService.add(city);
        pointService.add(new Point("BANK Levoberezhnyy", PointType.BANK, PointAbility.SENDING_AND_RECEIVING, "St. Majakovskogo, 5", city));
        pointService.add(new Point("Bystrodengi", PointType.STORE, PointAbility.RECEIVING, "St. Bogdana Khmelnickogo, 4", city));

        country = new Country("Belarus");
        city = new City("Minsk",country);

        countryService.add(country);
        cityService.add(city);
        pointService.add(new Point("VTB BANK (BELARUS)", PointType.BANK, PointAbility.SENDING, "Selitskogo ulitsa 65 ", city));

        country = new Country("USA");
        city = new City("New-York",country);

        countryService.add(country);
        cityService.add(city);
        pointService.add(new Point("MONEYGRAM OF NEW YORK - #20 - CHTOWN", PointType.STORE, PointAbility.SENDING_AND_RECEIVING, "79 DIVISION ST", city));
    }
}
