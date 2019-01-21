package hello.dao;

import hello.entities.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    public Iterable<City> findByNameContainingIgnoreCase(String name);

}
