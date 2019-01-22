package hello;

import hello.entities.City;
import hello.entities.Country;
import hello.entities.Point;
import hello.entities.pointfields.PointAbility;
import hello.entities.pointfields.PointType;
import hello.services.impl.CityServiceImpl;
import hello.services.impl.CountryServiceImpl;
import hello.services.impl.PointServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
        return pointService.findByNameContainingIgnoreCase(name);
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

        country = new Country("Belarus");
        city = new City("Minsk",country);

        countryService.add(country);
        cityService.add(city);
        pointService.add(new Point("VTB BANK (BELARUS)", PointType.BANK, PointAbility.SENDING, "Selitskogo ulitsa 65 ", city));

        country = new Country("USA");
        city = new City("New-York",country);

        countryService.add(country);
        cityService.add(city);
        pointService.add(new Point("MONEYGRAM OF NEW YORK - #20 - CHTOWN ", PointType.STORE, PointAbility.SENDING_AND_RECEIVING, "79 DIVISION ST", city));
    }
/*
    @GetMapping("/testdelete")
    public void setRepository(@RequestParam(value="name", required=false, defaultValue="") String name){
        countryService.deleteByName(name);
    }
    */
}
