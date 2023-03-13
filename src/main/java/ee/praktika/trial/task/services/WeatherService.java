package ee.praktika.trial.task.services;

import ee.praktika.trial.task.models.Weather;

import java.util.List;

public interface WeatherService {

    public List<Weather> readWeather(String city, String vehicle);

    public Weather readSpecificWeather(Long id);

    public Weather createWeather(Weather weather);

    public void deleteWeather(Long id);

    public void updateWeather(Long id, Weather weather);

}
