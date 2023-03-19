package ee.praktika.trial.task.repository;

import ee.praktika.trial.task.models.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Weather repository, used for getting the weather objects from the weather database.
 */
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    List<Weather> findAllByName(String city);

}
