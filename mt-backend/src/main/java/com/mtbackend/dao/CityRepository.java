package com.mtbackend.dao;

import com.mtbackend.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    public Iterable<City> findByNameContainingIgnoreCase(String name);

}
