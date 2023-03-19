package ee.praktika.trial.task.services.impl;


import ee.praktika.trial.task.exceptions.InvalidCityNameException;
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
    private static final double TALLINN_CAR_COST = 4;
    private static final double TALLINN_SCOOTER_COST = 3.5;
    private static final double TALLINN_BIKE_COST = 3;
    private static final double TARTU_CAR_COST = 3.5;
    private static final double TARTU_SCOOTER_COST = 3;
    private static final double TARTU_BIKE_COST = 2.5;
    private static final double PARNU_CAR_COST = 3;
    private static final double PARNU_SCOOTER_COST = 2.5;
    private static final double PARNU_BIKE_COST = 2;
    private static final double VERY_COLD_AIR_TEMPERATURE_COST = 1;
    private static final double COLD_AIR_TEMPERATURE_COST = 0.5;
    private static final double HIGH_WIND_SPEED_COST = 0.5;
    private static final double SNOW_OR_SLEET_WEATHER_COST = 1;
    private static final double RAIN_WEATHER_COST = 0.5;
    private static final String TALLINN = "Tallinn";
    private static final String PARNU = "Pärnu";
    private static final String TARTU = "Tartu";
    private static final double VERY_COLD_AIR_TEMPERATURE = -10;
    private static final double COLD_AIR_TEMPERATURE = 0;
    private static final double HIGH_WIND_SPEED_MAX = 20;
    private static final double HIGH_WIND_SPEED_MIN = 10;
    private static final double ISNULL = 0.0f;

    // Variables
    private final WeatherRepository weatherRepository;


    @Override
    public Fee calculateCost(String city, String vehicle) {
        Fee fee = new Fee();
        String cityToSearch = renameCity(city);
        List<Weather> info = weatherRepository.findAllByName(cityToSearch);
        info.sort(Comparator.comparing(Weather::getTime));
        calculateFees(city, vehicle, info, fee);
        return fee;
    }

    @Override
    public String renameCity(String city) throws InvalidCityNameException {
        String cityToSearchBy = "";
        if (city.equals(TALLINN)) {
            cityToSearchBy = "Tallinn-Harku";
        }
        if (city.equals(TARTU)) {
            cityToSearchBy = "Tartu-Tõravere";
        }
        if (city.equals(PARNU)) {
            cityToSearchBy = PARNU;
        }
        if (cityToSearchBy.isEmpty()) {
            throw new InvalidCityNameException();
        }
        return cityToSearchBy;
    }

    @Override
    public void calculateFees(String city, String vehicle, List<Weather> info, Fee fee) {
        if (vehicle.equals("Car")) {
            if (city.equals(TALLINN)) {
                fee.setRbf(TALLINN_CAR_COST);
            }
            if (city.equals(TARTU)) {
                fee.setRbf(TARTU_CAR_COST);
            }
            if (city.equals(PARNU)) {
                fee.setRbf(PARNU_CAR_COST);
            }
        }
        if (vehicle.equals("Scooter")) {
            calculateAtef(info, fee);
            calculateWpef(info, fee);
            if (city.equals(TALLINN)) {
                fee.setRbf(TALLINN_SCOOTER_COST);
            }
            if (city.equals(TARTU)) {
                fee.setRbf(TARTU_SCOOTER_COST);
            }
            if (city.equals(PARNU)) {
                fee.setRbf(PARNU_SCOOTER_COST);
            }
        }
        if (vehicle.equals("Bike")) {
            calculateAtef(info, fee);
            calculateWsef(info, fee, vehicle);
            calculateWpef(info, fee);
            if (city.equals(TALLINN)) {
                fee.setRbf(TALLINN_BIKE_COST);
            }
            if (city.equals(TARTU)) {
                fee.setRbf(TARTU_BIKE_COST);
            }
            if (city.equals(PARNU)) {
                fee.setRbf(PARNU_BIKE_COST);
            }
        }

    }

    @Override
    public void calculateAtef(List<Weather> info, Fee fee) {
        float airTemp = info.get(0).getAirTemperature();
        if (airTemp != ISNULL) {
            if (airTemp < VERY_COLD_AIR_TEMPERATURE) {
                fee.setAtef(VERY_COLD_AIR_TEMPERATURE_COST);
            } else if (airTemp < COLD_AIR_TEMPERATURE) {
                fee.setAtef(COLD_AIR_TEMPERATURE_COST);
            } else {
                fee.setAtef(0);
            }
        }
    }

    @Override
    public void calculateWsef(List<Weather> info, Fee fee, String vehicle) {
        float windSpeed = info.get(0).getWindSpeed();
        if (windSpeed != ISNULL) {
            if (windSpeed >= HIGH_WIND_SPEED_MIN && windSpeed <= HIGH_WIND_SPEED_MAX) {
                fee.setWsef(HIGH_WIND_SPEED_COST);
            } else if (windSpeed > HIGH_WIND_SPEED_MAX && vehicle.equals("Bike")) {
                throw new VehicleIsForbiddenException();
            }
        }
    }

    @Override
    public void calculateWpef(List<Weather> info, Fee fee) {
        List<String> listOfSnowAndFleetPhenomenons = new ArrayList<>(List.of("Light sleet", "Moderate sleet", "Light snowfall", "Moderate Snowfall",
                "Heavy snowfall", "Blowing snow", "Drifting snow"));
        List<String> listOfRainPhenomenons = new ArrayList<>(List.of("Light shower", "Moderate shower", "Heavy shower", "Light rain", "Moderate rain", "Heavy rain"));
        List<String> listOfForbiddenPhenomenons = new ArrayList<>(List.of("Glaze", "Hail", "Thunder", "Thunderstorm"));
        String phenomenon = info.get(0).getPhenomenon();
        if (listOfSnowAndFleetPhenomenons.contains(phenomenon)) {
            fee.setWpef(SNOW_OR_SLEET_WEATHER_COST);
        } else if (listOfRainPhenomenons.contains(phenomenon)) {
            fee.setWpef(RAIN_WEATHER_COST);
        } else if (listOfForbiddenPhenomenons.contains(phenomenon)) {
            throw new VehicleIsForbiddenException();
        }
    }

}
