package ee.praktika.trial.task.services.impl;

import ee.praktika.trial.task.exceptions.InvalidCityNameException;
import ee.praktika.trial.task.models.Fee;
import ee.praktika.trial.task.models.Station;
import ee.praktika.trial.task.models.Weather;
import ee.praktika.trial.task.repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FeeServiceImplTest {

    @Mock
    private WeatherRepository weatherRepository;

    @InjectMocks
    private FeeServiceImpl feeService;

    @Test
    public void renameCityIfTallinn() {
        String tallinn = "Tallinn";
        String expectedResult = "Tallinn-Harku";

        String result = feeService.renameCity(tallinn);

        assertEquals(expectedResult, result);
    }

    @Test
    public void renameCityIfTartu() {
        String tartu = "Tartu";
        String expectedResult = "Tartu-Tõravere";

        String result = feeService.renameCity(tartu);

        assertEquals(expectedResult, result);
    }

    @Test
    public void renameCityIfParnu() {
        String parnu = "Pärnu";
        String expectedResult = "Pärnu";

        String result = feeService.renameCity(parnu);

        assertEquals(expectedResult, result);
    }

//    @Test
//    public void renameCityIfInvalid() {
//        String tapa = "Tapa";
//        String expectedMessage = "The city input has to be either Tallinn, Tartu or Pärnu!";
//
//        Throwable exception = assertThrows(InvalidCityNameException.class, () -> feeService.renameCity(tapa));
//        assertThrows(InvalidCityNameException.class, feeService.renameCity(tapa));
//    }

    @Test
    public void calculateCostTallinnWithCar() {
        String city = "Tallinn";
        String vehicle = "Car";
        List<Weather> info = createWeatherInfoList();
        when(weatherRepository.findAllByName(any())).thenReturn(info);

        Fee result = feeService.calculateCost(city, vehicle);

    }

    private static List<Weather> createWeatherInfoList() {

    }

    private static Weather createWeather() {
        Weather weather

    }

    private static Station createStation() {
        Station station = new Station();
        String name = "Tartu-Tõravere";
        Integer wmocode = 26242;
        String phenomenon = "Few clouds";
        Float airtemperature = 4.0;
        Float windspeed = Float.parseFloat("2.3");
        station.setName(name);
        station.setWmocode(wmocode);
        station.setPhenomenon(phenomenon);
        station.setWindspeed(windspeed);


}
