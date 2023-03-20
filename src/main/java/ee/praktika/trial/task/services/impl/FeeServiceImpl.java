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

/**
 * Fee service implements Fee Service
 */
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


    /**
     * Method searches for weather data for that specific city, and takes the most recent weather data.
     * and calculates all the additional fees based on the weather data.
     * @param city to get delivery from.
     * @param vehicle to get delivery with.
     * @return The total fee for the delivery.
     */
    @Override
    public Fee calculateCost(String city, String vehicle) {
        Fee fee = new Fee();
        String cityToSearch = renameCity(city);
        List<Weather> info = weatherRepository.findAllByName(cityToSearch);
        info.sort(Comparator.comparing(Weather::getTime));
        calculateFees(city, vehicle, info, fee);
        return fee;
    }

    /**
     * @param city name of the city to get the delivery from.
     * @return name of the station where to get the data from.
     * @throws InvalidCityNameException if the city is not Pärnu, Tartu or Tallinn.
     */
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

    /**
     * Method checks for the according vehicle, if its Car Scooter or Bike
     * Afterwards the method checks which city the delivery is made in and calculates the:
     *
     *     Regional base fee - Set in stone price for vehicle and city
     * • In case City = Tallinn and:
     * Vehicle type = Car, then RBF = 4
     * Vehicle type = Scooter, then RBF = 3,5
     * Vehicle type = Bike, then RBF = 3
     * • In case City = Tartu and:
     * Vehicle type = Car, then RBF = 3,5
     * Vehicle type = Scooter, then RBF = 3
     * Vehicle type = Bike, then RBF = 2,5
     * • In case City = Pärnu and:
     * Vehicle type = Car, then RBF = 3
     * Vehicle type = Scooter, then RBF = 2,5
     * Vehicle type = Bike, then RBF = 2
     *
     *     ATEF - Fee based on air temperature for Scooter and Bike vehicle types
     * Air temperature is less than -10 C, then ATEF = 1
     * Air temperature is between -10 C and 0 C, then ATEF = 0,5
     *
     *     WSEF - Fee based on wind speed for Bike
     * Wind speed is between 10 and 20 , then WSEF = 0,5
     * In case of wind speed is greater than 20, then throw VehicleIsForbiddenException
     *
     *     WPEF - Fee based on weather phenomenon for Scooter and Bike vehicle types
     * Weather phenomenon is related to snow or sleet, then WPEF = 1
     * Weather phenomenon is related to rain, then WPEF = 0,5
     * In case the weather phenomenon is glaze, hail, or thunder, then throw VehicleIsForbiddenException
     *
     * @param city to make delivery in
     * @param vehicle to make delivery with
     * @param info latest weather data to work with and calculate all the fees
     * @param fee total fee of delivery
     */
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

    /**
     * ATEF - Fee based on air temperature for Scooter and Bike vehicle types
     * Air temperature is less than -10 C, then ATEF = 1
     * Air temperature is between -10 C and 0 C, then ATEF = 0,5
     *
     * @param info weather info
     * @param fee to raise ATEF price if the conditions are met
     */
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

    /**
     * WSEF - Fee based on wind speed for Bike
     * Wind speed is between 10 and 20 , then WSEF = 0,5
     * In case of wind speed is greater than 20, then throw VehicleIsForbiddenException
     *
     *
     * @param info weather info
     * @param fee to raise WSEF price if the conditions are met
     * @param vehicle to check if its too windy to ride with a bike.
     */
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

    /**
     * WPEF - Fee based on weather phenomenon for Scooter and Bike vehicle types
     * Weather phenomenon is related to snow or sleet, then WPEF = 1
     * Weather phenomenon is related to rain, then WPEF = 0,5
     * In case the weather phenomenon is glaze, hail, or thunder, then throw VehicleIsForbiddenException
     * @param info weather info
     * @param fee to raise WPEF price if the conditions are met.
     */
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
