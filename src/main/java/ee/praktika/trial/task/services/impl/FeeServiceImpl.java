package ee.praktika.trial.task.services.impl;


import ee.praktika.trial.task.exceptions.VehicleIsForbiddenException;
import ee.praktika.trial.task.models.Fee;
import ee.praktika.trial.task.models.Weather;
import ee.praktika.trial.task.repository.WeatherRepository;
import ee.praktika.trial.task.services.FeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
@AllArgsConstructor
public class FeeServiceImpl implements FeeService {

    // Constants
    private static final double TALLINNCARCOST = 4;
    private static final double TALLINNSCOOTERCOST = 3.5;
    private static final double TALLINNBIKECOST = 3;
    private static final double TARTUCARCOST = 3.5;
    private static final double TARTUSCOOTERCOST = 3;
    private static final double TARTUBIKECOST = 2.5;
    private static final double PARNUCARCOST = 3;
    private static final double PARNUSCOOTERCOST = 2.5;
    private static final double PARNUBIKECOST = 2;
    private static final double VERYCOLDAIRTEMPERATURECOST = 1;
    private static final double COLDAIRTEMPERATURECOST = 0.5;
    private static final double HIGHWINDSPEEDCOST = 0.5;
    private static final double SNOWORSLEETWEATHERCOST = 1;
    private static final double RAINWEATHERCOST = 0.5;
    private static final String TALLINN = "Tallinn";
    private static final String PARNU = "Pärnu";
    private static final String TARTU = "Tartu";
    private static final double VERYCOLDAIRTEMPERATURE = -10;
    private static final double COLDAIRTEMPERATURE = 0;
    private static final double HIGHWINDSPEEDMAX = 20;
    private static final double HIGHWINDSPEEDMIN = 10;
    private static final double ISNULL = 0.0f;

    // Variables
    private final WeatherRepository weatherRepository;
    private final Fee fee;
    private String cityToSearchBy;


    @Override
    public Fee calculateCost(String city, String vehicle) {
        renameCity(city);
        List<Weather> info = weatherRepository.findAllByName(cityToSearchBy);
        info.sort(Comparator.comparing(Weather::getTime));
        calculateFees(city, vehicle, info);
        System.out.println(fee.getTotalCost());
        return fee;
    }

    @Override
    public void renameCity(String city) {
        if (city.equals(TALLINN)) {
            cityToSearchBy = "Tallinn-Harku";
        }
        if (city.equals(TARTU)) {
            cityToSearchBy = "Tartu-Tõravere";
        }
        if (city.equals(PARNU)) {
            cityToSearchBy = PARNU;
        }
    }

    @Override
    public void calculateFees(String city, String vehicle, List<Weather> info) {
        if (vehicle.equals("Car")) {
            if (city.equals(TALLINN)) {
                fee.setRbf(TALLINNCARCOST);
            }
            if (city.equals(TARTU)) {
                fee.setRbf(TARTUCARCOST);
            }
            if (city.equals(PARNU)) {
                fee.setRbf(PARNUCARCOST);
            }
        }
        if (vehicle.equals("Scooter")) {
            calculateAtef(info);
            calculateWpef(info);
            if (city.equals(TALLINN)) {
                fee.setRbf(TALLINNSCOOTERCOST);
            }
            if (city.equals(TARTU)) {
                fee.setRbf(TARTUSCOOTERCOST);
            }
            if (city.equals(PARNU)) {
                fee.setRbf(PARNUSCOOTERCOST);
            }
        }
        if (vehicle.equals("Bike")) {
            calculateAtef(info);
            calculateWsef(info);
            calculateWpef(info);
            if (city.equals(TALLINN)) {
                fee.setRbf(TALLINNBIKECOST);
            }
            if (city.equals(TARTU)) {
                fee.setRbf(TARTUBIKECOST);
            }
            if (city.equals(PARNU)) {
                fee.setRbf(PARNUBIKECOST);
            }
        }

    }

    @Override
    public void calculateAtef(List<Weather> info) {
        float airTemp = info.get(0).getAirTemperature();
        if (airTemp != ISNULL) {
            if (airTemp < VERYCOLDAIRTEMPERATURE) {
                fee.setAtef(VERYCOLDAIRTEMPERATURECOST);
            } else if (airTemp >= VERYCOLDAIRTEMPERATURE && airTemp <= COLDAIRTEMPERATURE) {
                fee.setAtef(COLDAIRTEMPERATURECOST);
            }
        }
    }

    @Override
    public void calculateWsef(List<Weather> info) {
        float windSpeed = info.get(0).getWindSpeed();
        if (windSpeed != ISNULL) {
            if (windSpeed >= HIGHWINDSPEEDMIN && windSpeed <= HIGHWINDSPEEDMAX) {
                fee.setWsef(HIGHWINDSPEEDCOST);
            } else if (windSpeed > HIGHWINDSPEEDMAX) {
                throw new VehicleIsForbiddenException();
            }
        }
    }

    @Override
    public void calculateWpef(List<Weather> info) {
        List<String> listOfSnowAndSnowPhenomenons = new ArrayList<>(List.of("Light sleet", "Moderate sleet", "Light snowfall", "Moderate Snowfall",
                "Heavy snowfall", "Blowing snow", "Drifting snow"));
        List<String> listOfRainPhenomenons = new ArrayList<>(List.of("Light shower", "Moderate shower", "Heavy shower", "Light rain", "Moderate rain", "Heavy rain"));
        List<String> listOfForbiddenPhenomenons = new ArrayList<>(List.of("Glaze", "Hail", "Thunder", "Thunderstorm"));
        String phenomenon = info.get(0).getPhenomenon();
        if (listOfSnowAndSnowPhenomenons.contains(phenomenon)) {
            fee.setWpef(SNOWORSLEETWEATHERCOST);
        } else if (listOfRainPhenomenons.contains(phenomenon)) {
            fee.setWpef(RAINWEATHERCOST);
        } else if (listOfForbiddenPhenomenons.contains(phenomenon)) {
            throw new VehicleIsForbiddenException();
        }
    }

}
