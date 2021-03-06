package com.mtwebapp.server;

import com.mtwebapp.server.entities.Point;
import com.mtwebapp.server.services.impl.CityServiceImpl;
import com.mtwebapp.server.services.impl.CountryServiceImpl;
import com.mtwebapp.server.services.impl.PointServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("points")
@Slf4j
public class PointRestController {

    private final CityServiceImpl cityService;
    private final CountryServiceImpl countryService;
    private final PointServiceImpl pointService;

    @Autowired
    public PointRestController(final CityServiceImpl cityService,
                               final CountryServiceImpl countryService,
                               final PointServiceImpl pointService) {
        this.cityService = cityService;
        this.countryService = countryService;
        this.pointService = pointService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Point>> getPointByFilter(
            @RequestParam(value = "text", required = false) String filter) {

        List<Point> resultPoints = pointService.findByCountryAndCityFilter(filter);
        CacheControl cache = CacheControl.maxAge(30, TimeUnit.MINUTES);

						log.info("Get points by filter='" + filter + "' : " + resultPoints);
        return ResponseEntity.ok()
                .cacheControl(cache)
                .body(resultPoints);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> addTodoItem(@RequestBody final Point point) {
        pointService.add(point);
        log.info("Point saved: " + point.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}