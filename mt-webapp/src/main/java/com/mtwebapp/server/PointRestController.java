package com.mtwebapp.server;

import com.mtwebapp.server.entities.Point;
import com.mtwebapp.server.services.impl.CityServiceImpl;
import com.mtwebapp.server.services.impl.CountryServiceImpl;
import com.mtwebapp.server.services.impl.PointServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("points")
public class PointRestController {

    private final CityServiceImpl cityService;
    private final CountryServiceImpl countryService;
    private final PointServiceImpl pointService;
    private final Logger logger = LoggerFactory.getLogger(PointRestController.class);

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
        logger.info("Get points by filter='"+filter+"' : " + resultPoints);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.noCache())
                .body(resultPoints);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> addTodoItem(@RequestBody final Point point) {
        pointService.add(point);
        logger.info("Point saved: " + point.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}