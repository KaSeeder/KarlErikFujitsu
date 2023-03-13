package ee.praktika.trial.task.controllers;

import ee.praktika.trial.task.models.Fee;
import ee.praktika.trial.task.models.Weather;
import ee.praktika.trial.task.services.FeeService;
import ee.praktika.trial.task.services.impl.WeatherAPIServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/fee")
@AllArgsConstructor
public class FeeController {

    private final FeeService feeService;


    @GetMapping
    public ResponseEntity<Fee> getWeather(@RequestParam(name = "city") String city,
                                          @RequestParam(name = "vehicle") String vehicle) throws MalformedURLException, JAXBException {
        return new ResponseEntity<>(
                feeService.calculateCost(city, vehicle),
                HttpStatus.OK);
    }
}
