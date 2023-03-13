package ee.praktika.trial.task.services.impl;

import ee.praktika.trial.task.exceptions.EntityNotFoundException;
import ee.praktika.trial.task.models.Weather;
import ee.praktika.trial.task.repository.WeatherRepository;
import ee.praktika.trial.task.services.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    @Override
    public List<Weather> readWeather(String city, String vehicle) {
        return weatherRepository.findAll();
    }

    @Override
    public Weather readSpecificWeather(Long id) {
        return weatherRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Weather createWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

    @Override
    public void deleteWeather(Long id) {
        weatherRepository.deleteById(id);
    }

    @Override
    public void updateWeather(Long id, Weather weather) {
        Weather oldHello = weatherRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (weather.getName() != null) {
            oldHello.setName(weather.getName());
        }
        if (weather.getWmocode() != null) {
            oldHello.setWmocode(weather.getWmocode());
        }
        if (weather.getAirTemperature() != null) {
            oldHello.setAirTemperature(weather.getAirTemperature());
        }
        if (weather.getWindSpeed() != null) {
            oldHello.setWindSpeed(weather.getWindSpeed());
        }
        if (weather.getPhenomenon() != null) {
            oldHello.setPhenomenon(weather.getPhenomenon());
        }
        if (weather.getTime() != null) {
            oldHello.setTime(weather.getTime());
        }
        weatherRepository.save(oldHello);
    }

}
