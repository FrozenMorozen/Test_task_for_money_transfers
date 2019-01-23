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

            // Добавить пункты с фильтром по стране
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

}
