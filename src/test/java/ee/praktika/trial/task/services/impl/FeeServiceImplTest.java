package ee.praktika.trial.task.services.impl;

import ee.praktika.trial.task.exceptions.InvalidCityNameException;
import ee.praktika.trial.task.repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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



}
