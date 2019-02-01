package com.mtwebapp.server;

import com.mtwebapp.server.entities.City;
import com.mtwebapp.server.entities.Country;
import com.mtwebapp.server.entities.Point;
import com.mtwebapp.server.entities.pointfields.PointAbility;
import com.mtwebapp.server.entities.pointfields.PointType;
import com.mtwebapp.server.services.impl.CityServiceImpl;
import com.mtwebapp.server.services.impl.CountryServiceImpl;
import com.mtwebapp.server.services.impl.PointServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableScheduling
public class SpringBootGwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGwtApplication.class, args);
    }
}
