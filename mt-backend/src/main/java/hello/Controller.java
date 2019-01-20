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
    public void setRepository(){

//        cityService.add(new City("New York"));
//        cityService.add(new City("New Angeles"));

/*        countryService.add(new Country("Russia"));
        City moscow = new City("Moscow",countryService.getCountryByName("Russia"));
        cityService.add(new City("Moscow",countryService.getCountryByName("Russia")));*/

        Country russia = new Country("Russia");
        countryService.add(russia);

        City moscow = new City("Moscow",russia);
        cityService.add(moscow);

        pointService.add(new Point("Euroset", PointType.STORE, PointAbility.SENDING_AND_RECEIVING, "St. Pervomajskaja, 104/1",moscow));

        countryService.add(new Country("Belorussia"));
        countryService.add(new Country("USA"));
        countryService.add(new Country("UAE"));
    }
/*
    @GetMapping("/testdelete")
    public void setRepository(@RequestParam(value="name", required=false, defaultValue="") String name){
        countryService.deleteByName(name);
    }
    */
}
