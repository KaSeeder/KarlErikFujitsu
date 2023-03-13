package ee.praktika.trial.task.services.impl;


import ee.praktika.trial.task.models.Fee;
import ee.praktika.trial.task.models.Weather;
import ee.praktika.trial.task.repository.WeatherRepository;
import ee.praktika.trial.task.services.FeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class FeeServiceImpl implements FeeService {

    private final WeatherRepository weatherRepository;

    @Override
    public Fee calculateCost(String city, String vehicle) {
        if (city.equals("Tallinn")) {
            city = "Tallinn-Harku";
        }
        if (city.equals("Tartu")) {
            city = "Tartu-Tõravere";
        }
        List<Weather> info = weatherRepository.findAllByName(city);
        info.sort(Comparator.comparing(Weather::getTime));
        System.out.println(info.get(0));
        return null;
    }
}
