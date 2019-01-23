package com.mtbackend;

import com.mtbackend.entities.City;
import com.mtbackend.entities.Country;
import com.mtbackend.entities.Point;
import com.mtbackend.entities.pointfields.PointAbility;
import com.mtbackend.entities.pointfields.PointType;
import com.mtbackend.services.impl.CityServiceImpl;
import com.mtbackend.services.impl.CountryServiceImpl;
import com.mtbackend.services.impl.PointServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        CountryServiceImpl countryService = context.getBean(CountryServiceImpl.class);
        CityServiceImpl cityService = context.getBean(CityServiceImpl.class);
        PointServiceImpl pointService = context.getBean(PointServiceImpl.class);

        pointService.clear();
        cityService.clear();
        countryService.clear();

        generateTestData(countryService, cityService, pointService);
    }

    /**
     * Сгенерировать тестовые данные
     */
    public static void generateTestData(CountryServiceImpl countryService, CityServiceImpl cityService, PointServiceImpl pointService){

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
