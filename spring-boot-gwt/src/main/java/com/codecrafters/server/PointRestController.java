package com.codecrafters.server;

import com.codecrafters.server.entities.City;
import com.codecrafters.server.entities.Country;
import com.codecrafters.server.entities.Point;
import com.codecrafters.server.services.impl.CityServiceImpl;
import com.codecrafters.server.services.impl.CountryServiceImpl;
import com.codecrafters.server.services.impl.PointServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("points")
public class PointRestController {

    private final CityServiceImpl cityService;

    private final CountryServiceImpl countryService;

    private final PointServiceImpl pointService;

    private final Logger logger = LoggerFactory.getLogger(PointRestController.class);

    @Autowired
    public PointRestController(final CityServiceImpl cityService
                            ,final CountryServiceImpl countryService
                            ,final PointServiceImpl pointService) {
        this.cityService = cityService;
        this.countryService = countryService;
        this.pointService = pointService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Point>> getPointByFilter(@RequestParam(value="text", required=false) String filter){

            Set<Point> resultPoints = new HashSet<>();

            // Отфильтровать пункты по городам
            for (City city: cityService.findCityByFilter(filter)) {
                resultPoints.addAll(pointService.findByCity(city));
            }

            // Добавить пункты с фильтром по стране
            for (Country country: countryService.findCountryByFilter(filter)) {
                for (City city: cityService.findCitiesByCountry(country)) {
                    resultPoints.addAll(pointService.findByCity(city));
                }
            }

            List<Point> points = new ArrayList<>(resultPoints);
            Collections.sort(points);

            return ResponseEntity.ok()
                    .cacheControl(CacheControl.noCache())
                    .body(points);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> addTodoItem(@RequestBody final Point point) {
        pointService.add(point);
        logger.info("Point saved: " + point.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
