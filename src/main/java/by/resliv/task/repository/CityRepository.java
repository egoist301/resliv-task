package by.resliv.task.repository;

import by.resliv.task.repository.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByNameStartingWithIgnoreCase(String name);
    boolean existsByName(String name);
    boolean existsByNameIsAndIdNot(String name, Long id);
}
