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

    /**
     * @param city to get the information from
     * @param vehicle to calculate additional fees
     * @return List of weather objects
     */
    @GetMapping
    public ResponseEntity<List<Weather>> getWeather(@RequestParam(name = "city") String city,
                                                    @RequestParam(name = "vehicle") String vehicle) {
        return new ResponseEntity<>(
                weatherService.readWeather(city, vehicle),
                HttpStatus.OK);
    }

    /**
     * @param id of the weather object to be searched for
     * @return the specified weather object
     */
    @GetMapping("/{id}")
    public ResponseEntity<Weather> getSpecificWeather(@PathVariable Long id) {
        return new ResponseEntity<>(
                weatherService.readSpecificWeather(id),
                HttpStatus.OK);
    }

    /**
     *
     * @param weather to be posted into the weather service
     * @return if the weather was posted into the weather service
     */
    @PostMapping
    public ResponseEntity<Weather> postWeather (@RequestBody Weather weather) {
        return new ResponseEntity<>(
                weatherService.createWeather(weather),
                HttpStatus.CREATED);
    }

    /**
     *
     * @param id of the weather object to be deleted.
     * @return void
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHello(@PathVariable Long id) {
        weatherService.deleteWeather(id);
        return new ResponseEntity<>(
                HttpStatus.NO_CONTENT);
    }

    /**
     *
     * @param weather object to update with
     * @param id of the weather to be changed
     * @return void
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateHello(@RequestBody Weather weather, @PathVariable Long id) {
        weatherService.updateWeather(id, weather);
        return new ResponseEntity<>(
                HttpStatus.NO_CONTENT);
    }


}
