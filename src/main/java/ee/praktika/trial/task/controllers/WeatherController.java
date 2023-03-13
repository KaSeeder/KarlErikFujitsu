package ee.praktika.trial.task.controllers;


import ee.praktika.trial.task.models.Weather;
import ee.praktika.trial.task.services.WeatherService;
import ee.praktika.trial.task.services.impl.WeatherAPIServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/weather")
@AllArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    WeatherAPIServiceImpl weatherAPIService;


    @GetMapping
    public ResponseEntity<List<Weather>> getWeather(@RequestParam(name = "city") String city,
                                                    @RequestParam(name = "vehicle") String vehicle) throws MalformedURLException, JAXBException {
        return new ResponseEntity<>(
                weatherService.readWeather(city, vehicle),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Weather> getSpecificWeather(@PathVariable Long id) {
        return new ResponseEntity<>(
                weatherService.readSpecificWeather(id),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Weather> postWeather (@RequestBody Weather weather) {
        return new ResponseEntity<>(
                weatherService.createWeather(weather),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHello(@PathVariable Long id) {
        weatherService.deleteWeather(id);
        return new ResponseEntity<>(
                HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateHello(@RequestBody Weather weather, @PathVariable Long id) {
        weatherService.updateWeather(id, weather);
        return new ResponseEntity<>(
                HttpStatus.NO_CONTENT);
    }


}
