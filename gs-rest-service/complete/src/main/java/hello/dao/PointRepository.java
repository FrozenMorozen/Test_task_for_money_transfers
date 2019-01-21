package hello.dao;

import hello.entities.Point;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository  extends CrudRepository<Point, Long> {

    public Iterable<Point> findByNameContainingIgnoreCase(String name);

}