package ee.praktika.trial.task.services.impl;

import ee.praktika.trial.task.exceptions.InvalidCityNameException;
import ee.praktika.trial.task.exceptions.VehicleIsForbiddenException;
import ee.praktika.trial.task.models.Fee;
import ee.praktika.trial.task.models.Weather;
import ee.praktika.trial.task.repository.WeatherRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FeeServiceImplTest {

    @Mock
    private WeatherRepository weatherRepository;

    @InjectMocks
    private FeeServiceImpl feeService;

    @Test
    public void renameCityIfTallinnTest() {
        String tallinn = "Tallinn";
        String expectedResult = "Tallinn-Harku";

        String result = feeService.renameCity(tallinn);

        assertEquals(expectedResult, result);
    }

    @Test
    public void renameCityIfTartuTest() {
        String tartu = "Tartu";
        String expectedResult = "Tartu-Tõravere";

        String result = feeService.renameCity(tartu);

        assertEquals(expectedResult, result);
    }

    @Test
    public void renameCityIfParnuTest() {
        String parnu = "Pärnu";
        String expectedResult = "Pärnu";

        String result = feeService.renameCity(parnu);

        assertEquals(expectedResult, result);
    }

    @Test
    public void renameCityIfInvalidTest() {
        String tapa = "Tapa";

        assertThrows(InvalidCityNameException.class, () -> feeService.renameCity(tapa));
    }

    @Test
    public void calculateRBFCostTallinnWithCarTest() {
        String city = "Tallinn";
        String vehicle = "Car";
        List<Weather> info = createWeatherInfoList();

        Fee expected = new Fee();
        expected.setRbf(4);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getRbf(), result.getRbf());
    }

    @Test
    public void calculateRBFCostTartuWithCarTest() {
        String city = "Tartu";
        String vehicle = "Car";
        List<Weather> info = createWeatherInfoList();

        Fee expected = new Fee();
        expected.setRbf(3.5);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getRbf(), result.getRbf());
    }

    @Test
    public void calculateRBFCostParnuWithCarTest() {
        String city = "Pärnu";
        String vehicle = "Car";
        List<Weather> info = createWeatherInfoList();

        Fee expected = new Fee();
        expected.setRbf(3);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getRbf(), result.getRbf());
    }

    @Test
    public void calculateRBFCostTallinnWithScooterTest() {
        String city = "Tallinn";
        String vehicle = "Scooter";
        List<Weather> info = createWeatherInfoList();

        Fee expected = new Fee();
        expected.setRbf(3.5);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getRbf(), result.getRbf());
    }

    @Test
    public void calculateRBFCostTartuWithScooterTest() {
        String city = "Tartu";
        String vehicle = "Scooter";
        List<Weather> info = createWeatherInfoList();

        Fee expected = new Fee();
        expected.setRbf(3);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getRbf(), result.getRbf());
    }

    @Test
    public void calculateRBFCostParnuWithScooterTest() {
        String city = "Pärnu";
        String vehicle = "Scooter";
        List<Weather> info = createWeatherInfoList();

        Fee expected = new Fee();
        expected.setRbf(2.5);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getRbf(), result.getRbf());
    }

    @Test
    public void calculateRBFCostTallinnWithBikeTest() {
        String city = "Tallinn";
        String vehicle = "Bike";
        List<Weather> info = createWeatherInfoList();

        Fee expected = new Fee();
        expected.setRbf(3);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getRbf(), result.getRbf());
    }

    @Test
    public void calculateRBFCostTartuWithBikeTest() {
        String city = "Tartu";
        String vehicle = "Bike";
        List<Weather> info = createWeatherInfoList();

        Fee expected = new Fee();
        expected.setRbf(2.5);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getRbf(), result.getRbf());
    }

    @Test
    public void calculateRBFCostParnuWithBikeTest() {
        String city = "Pärnu";
        String vehicle = "Bike";
        List<Weather> info = createWeatherInfoList();

        Fee expected = new Fee();
        expected.setRbf(2);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getRbf(), result.getRbf());
    }

    @Test
    public void calculateATEFCostWithVeryColdAirTemperatureAndCarTest() {
        String city = "Tallinn";
        String vehicle = "Car";
        List<Weather> info = createWeatherInfoList();

        Fee expected = new Fee();
        expected.setAtef(0);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getAtef(), result.getAtef());
    }

    @Test
    public void calculateATEFCostWithVeryColdAirTemperatureAndScooterTest() {
        String city = "Tallinn";
        String vehicle = "Scooter";
        List<Weather> info = createWeatherInfoList();

        Fee expected = new Fee();
        expected.setAtef(1);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getAtef(), result.getAtef());
    }

    @Test
    public void calculateATEFCostWithVeryColdAirTemperatureAndBikeTest() {
        String city = "Tallinn";
        String vehicle = "Bike";
        List<Weather> info = createWeatherInfoList();


        Fee expected = new Fee();
        expected.setAtef(1);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getAtef(), result.getAtef());
    }

    @Test
    public void calculateATEFCostWithColdAirTemperatureAndCarTest() {
        String city = "Tartu";
        String vehicle = "Car";
        List<Weather> info = createWeatherInfoList();

        Fee expected = new Fee();
        expected.setAtef(0);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getAtef(), result.getAtef());
    }

    @Test
    public void calculateATEFCostWithColdAirTemperatureAndScooterTest() {
        String city = "Tartu";
        String vehicle = "Scooter";
        Weather tartu = createWeatherTartu();
        List<Weather> info = new ArrayList<>();
        info.add(tartu);

        Fee expected = new Fee();
        expected.setAtef(0.5);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getAtef(), result.getAtef());
    }

    @Test
    public void calculateATEFCostWithColdAirTemperatureAndBikeTest() {
        String city = "Tartu";
        String vehicle = "Bike";
        Weather tartu = createWeatherTartu();
        List<Weather> info = new ArrayList<>();
        info.add(tartu);

        Fee expected = new Fee();
        expected.setAtef(0.5);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getAtef(), result.getAtef());
    }

    @Test
    public void calculateATEFCostWithNormalAirTemperatureAndCarTest() {
        String city = "Pärnu";
        String vehicle = "Car";
        List<Weather> info = createWeatherInfoList();

        Fee expected = new Fee();
        expected.setAtef(0);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getAtef(), result.getAtef());
    }

    @Test
    public void calculateATEFCostWithNormalAirTemperatureAndScooterTest() {
        String city = "Pärnu";
        String vehicle = "Scooter";
        Weather parnu = createWeatherParnu();
        List<Weather> info = new ArrayList<>();
        info.add(parnu);

        Fee expected = new Fee();
        expected.setAtef(0);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getAtef(), result.getAtef());
    }

    @Test
    public void calculateWSEFCostWithBikeAndVeryHighWindSpeedThrowsErrorTest() {
        String city = "Pärnu";
        String vehicle = "Bike";
        Weather parnu = createWeatherParnu();
        List<Weather> info = new ArrayList<>();
        info.add(parnu);

        when(weatherRepository.findAllByName(any())).thenReturn(info);

        assertThrows(VehicleIsForbiddenException.class, () -> feeService.calculateCost(city, vehicle));
    }

    @Test
    public void calculateWSEFCostWithScooterAndVeryHighWindSpeedTest() {
        String city = "Pärnu";
        String vehicle = "Scooter";
        Weather parnu = createWeatherParnu();
        List<Weather> info = new ArrayList<>();
        info.add(parnu);

        Fee expected = new Fee();

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getWsef(),result.getWsef());
    }

    @Test
    public void calculateWSEFCostWithCarAndVeryHighWindSpeedTest() {
        String city = "Pärnu";
        String vehicle = "Car";
        Weather parnu = createWeatherParnu();
        List<Weather> info = new ArrayList<>();
        info.add(parnu);

        Fee expected = new Fee();

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getWsef(),result.getWsef());
    }

    @Test
    public void calculateWSEFCostWithBikeAndHighWindSpeedTest() {
        String city = "Tartu";
        String vehicle = "Bike";
        Weather tartu = createWeatherTartu();
        List<Weather> info = new ArrayList<>();
        info.add(tartu);

        Fee expected = new Fee();
        expected.setWsef(0.5);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getWsef(),result.getWsef());
    }

    @Test
    public void calculateWPEFCostWithBikeAndSnowOrSleetRelatedPhenomenonTest() {
        String city = "Pärnu";
        String vehicle = "Bike";
        Weather snowOrSleetRelatedWeather = createSnowOrSleetRelatedWeather();
        List<Weather> info = new ArrayList<>();
        info.add(snowOrSleetRelatedWeather);

        Fee expected = new Fee();
        expected.setWpef(1);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getWpef(),result.getWpef());
    }

    @Test
    public void calculateWPEFCostWithScooterAndSnowOrSleetRelatedPhenomenonTest() {
        String city = "Pärnu";
        String vehicle = "Scooter";
        Weather snowOrSleetRelatedWeather = createSnowOrSleetRelatedWeather();
        List<Weather> info = new ArrayList<>();
        info.add(snowOrSleetRelatedWeather);

        Fee expected = new Fee();
        expected.setWpef(1);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getWpef(),result.getWpef());
    }

    @Test
    public void calculateWPEFCostWithCarAndSnowOrSleetRelatedPhenomenonTest() {
        String city = "Pärnu";
        String vehicle = "Car";
        Weather snowOrSleetRelatedWeather = createSnowOrSleetRelatedWeather();
        List<Weather> info = new ArrayList<>();
        info.add(snowOrSleetRelatedWeather);

        Fee expected = new Fee();

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getWpef(),result.getWpef());
    }

    @Test
    public void calculateWPEFCostWithBikeAndRainRelatedPhenomenonTest() {
        String city = "Pärnu";
        String vehicle = "Bike";
        Weather rainRelatedWeather = createRainRelatedWeather();
        List<Weather> info = new ArrayList<>();
        info.add(rainRelatedWeather);

        Fee expected = new Fee();
        expected.setWpef(0.5);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getWpef(),result.getWpef());
    }

    @Test
    public void calculateWPEFCostWithScooterAndRainRelatedPhenomenonTest() {
        String city = "Pärnu";
        String vehicle = "Scooter";
        Weather rainRelatedWeather = createRainRelatedWeather();
        List<Weather> info = new ArrayList<>();
        info.add(rainRelatedWeather);

        Fee expected = new Fee();
        expected.setWpef(0.5);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getWpef(),result.getWpef());
    }

    @Test
    public void calculateWPEFCostWithCarAndRainRelatedPhenomenonTest() {
        String city = "Pärnu";
        String vehicle = "Car";
        Weather rainRelatedWeather = createRainRelatedWeather();
        List<Weather> info = new ArrayList<>();
        info.add(rainRelatedWeather);

        Fee expected = new Fee();

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getWpef(),result.getWpef());
    }

    @Test
    public void calculateWPEFCostWithBikeAndForbiddenPhenomenonTest() {
        String city = "Pärnu";
        String vehicle = "Bike";
        Weather forbiddenRelatedWeather = createForbiddenRelatedWeather();
        List<Weather> info = new ArrayList<>();
        info.add(forbiddenRelatedWeather);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        assertThrows(VehicleIsForbiddenException.class, () -> feeService.calculateCost(city, vehicle));

    }

    @Test
    public void calculateWPEFCostWithScooterAndForbiddenPhenomenonTest() {
        String city = "Pärnu";
        String vehicle = "Scooter";
        Weather forbiddenRelatedWeather = createForbiddenRelatedWeather();
        List<Weather> info = new ArrayList<>();
        info.add(forbiddenRelatedWeather);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        assertThrows(VehicleIsForbiddenException.class, () -> feeService.calculateCost(city, vehicle));

    }

    @Test
    public void calculateWPEFCostWithCarAndForbiddenPhenomenonTest() {
        String city = "Pärnu";
        String vehicle = "Car";
        Weather forbiddenRelatedWeather = createForbiddenRelatedWeather();
        List<Weather> info = new ArrayList<>();
        info.add(forbiddenRelatedWeather);

        Fee expected = new Fee();

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getWpef(),result.getWpef());
    }

    @Test
    public void calculateFeeCostWithCarAndTallinnWithVeryColdAirTemperatureAndLowWindSpeedTest() {
        String city = "Tallinn";
        String vehicle = "Car";
        List<Weather> info = createWeatherInfoList();

        Fee expected = new Fee();
        expected.setRbf(4);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getTotalCost(),result.getTotalCost());
    }

    @Test
    public void calculateFeeCostWithScooterAndTallinnWithVeryColdAirTemperatureAndLowWindSpeedTest() {
        String city = "Tallinn";
        String vehicle = "Scooter";
        List<Weather> info = createWeatherInfoList();

        Fee expected = new Fee();
        expected.setRbf(3.5);
        expected.setAtef(1);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getTotalCost(),result.getTotalCost());
    }

    @Test
    public void calculateFeeCostWithBikeAndTartuWithColdAirTemperatureAndHighWindSpeedTest() {
        String city = "Pärnu";
        String vehicle = "Bike";
        List<Weather> info = createWeatherInfoList();

        Fee expected = new Fee();
        expected.setRbf(2);
        expected.setAtef(0.5);
        expected.setWsef(0.5);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getTotalCost(),result.getTotalCost());
    }

    @Test
    public void calculateFeeCostWithBikeAndParnuWithColdAirTemperatureAndHighWindSpeedAndSnowRelatedPhenomenonTest() {
        String city = "Pärnu";
        String vehicle = "Bike";
        List<Weather> info = new ArrayList<>();
        Weather snowOrSleetRelatedWeather = createSnowOrSleetRelatedWeather();
        info.add(snowOrSleetRelatedWeather);

        Fee expected = new Fee();
        expected.setRbf(2);
        expected.setAtef(1);
        expected.setWsef(0.5);
        expected.setWpef(1);

        when(weatherRepository.findAllByName(any())).thenReturn(info);
        Fee result = feeService.calculateCost(city, vehicle);

        Assertions.assertEquals(expected.getTotalCost(),result.getTotalCost());
    }

    public static List<Weather> createWeatherInfoList() {
        Weather weatherTallinn = createWeatherTallinn();
        Weather weatherTartu = createWeatherTartu();
        Weather weatherParnu = createWeatherParnu();
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weatherTallinn);
        weatherList.add(weatherTartu);
        weatherList.add(weatherParnu);
        return weatherList;
    }

    private static Weather createWeatherTallinn() {
        Weather weather = new Weather();
        Date date = new Date();
        weather.setName("Tallinn-Harku");
        weather.setWmocode(26242);
        weather.setPhenomenon("Few clouds");
        weather.setAirTemperature(Float.parseFloat("-15"));
        weather.setWindSpeed(Float.parseFloat("3"));
        weather.setTime(date);
        return weather;
    }

    private static Weather createWeatherTartu() {
        Weather weather = new Weather();
        Date date = new Date();
        weather.setName("Tartu-Tõravere");
        weather.setWmocode(26242);
        weather.setPhenomenon("Few clouds");
        weather.setAirTemperature(Float.parseFloat("-5"));
        weather.setWindSpeed(Float.parseFloat("15"));
        weather.setTime(date);
        return weather;
    }

    private static Weather createWeatherParnu() {
        Weather weather = new Weather();
        Date date = new Date();
        weather.setName("Pärnu");
        weather.setWmocode(26242);
        weather.setPhenomenon("Few clouds");
        weather.setAirTemperature(Float.parseFloat("4.0"));
        weather.setWindSpeed(Float.parseFloat("30"));
        weather.setTime(date);
        return weather;
    }

    private static Weather createSnowOrSleetRelatedWeather() {
        Weather weather = new Weather();
        Date date = new Date();
        weather.setName("Pärnu");
        weather.setWmocode(26242);
        weather.setPhenomenon("Heavy snowfall");
        weather.setAirTemperature(Float.parseFloat("-11"));
        weather.setWindSpeed(Float.parseFloat("13"));
        weather.setTime(date);
        return weather;
    }

    private static Weather createRainRelatedWeather() {
        Weather weather = new Weather();
        Date date = new Date();
        weather.setName("Pärnu");
        weather.setWmocode(26242);
        weather.setPhenomenon("Light shower");
        weather.setAirTemperature(Float.parseFloat("4.0"));
        weather.setWindSpeed(Float.parseFloat("5"));
        weather.setTime(date);
        return weather;
    }

    private static Weather createForbiddenRelatedWeather() {
        Weather weather = new Weather();
        Date date = new Date();
        weather.setName("Pärnu");
        weather.setWmocode(26242);
        weather.setPhenomenon("Glaze");
        weather.setAirTemperature(Float.parseFloat("4.0"));
        weather.setWindSpeed(Float.parseFloat("5"));
        weather.setTime(date);
        return weather;
    }
}
