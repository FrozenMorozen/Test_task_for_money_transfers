package com.mtwebapp.server;

import com.mtwebapp.server.entities.City;
import com.mtwebapp.server.entities.Country;
import com.mtwebapp.server.entities.Point;
import com.mtwebapp.server.entities.pointfields.PointAbility;
import com.mtwebapp.server.entities.pointfields.PointType;
import com.mtwebapp.server.services.impl.CityServiceImpl;
import com.mtwebapp.server.services.impl.CountryServiceImpl;
import com.mtwebapp.server.services.impl.PointServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TestDataGenerator implements ApplicationRunner {

    private final CountryServiceImpl countryService;
    private final CityServiceImpl cityService;
    private final PointServiceImpl pointService;

    @Autowired
    public TestDataGenerator(CountryServiceImpl countryService, CityServiceImpl cityService, PointServiceImpl pointService) {
        this.countryService = countryService;
        this.cityService = cityService;
        this.pointService = pointService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        clearData();
        generateTestData();
    }

    private void clearData() {
        pointService.clear();
        cityService.clear();
        countryService.clear();
    }

    private void generateTestData() {

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

        country = new Country("Belgium");
        city = new City("Brussel",country);

        countryService.add(country);
        cityService.add(city);
        pointService.add(new Point("AM BANK", PointType.BANK, PointAbility.SENDING_AND_RECEIVING, "79 DIVISION ST", city));
    }
}
