package ee.praktika.trial.task.services.impl;

import ee.praktika.trial.task.models.Observations;
import ee.praktika.trial.task.models.Weather;
import ee.praktika.trial.task.repository.WeatherRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;


/**
 * Weather API service implements Weather Service
 */
@Service
@AllArgsConstructor
public class WeatherAPIServiceImpl {

    private final WeatherRepository weatherRepository;

    private final String LINK = "https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php";

    /**
     * Using CronJob, 15 minutes after a full hour it requests the Estonian Environment Agency
     * for information about the weather.
     * The method converts it from an XML file and makes the data into Observation Objects.
     * The Observation objects are looped through and made into Weather Objects, which are saved into the
     * weatherRepository.
     */
    @Scheduled(cron="0 15 * ? * *")
    public void getObservations() throws JAXBException, MalformedURLException {
        JAXBContext context = JAXBContext.newInstance(Observations.class);
        Unmarshaller un = context.createUnmarshaller();
        Observations observations = (Observations) un.unmarshal(new URL(LINK));
        for (int i = 0; i < observations.getStations().size(); i++) {
            Weather weather = new Weather(observations.getStations().get(i), observations.getTime());
            weatherRepository.save(weather);
        }
    }
}
