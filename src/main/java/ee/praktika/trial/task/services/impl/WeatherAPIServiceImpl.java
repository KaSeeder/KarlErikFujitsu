package ee.praktika.trial.task.services.impl;

import ee.praktika.trial.task.models.Observations;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * New and updated version of Impl.
 */
public class WeatherAPIServiceImpl {

    private static final String LINK = "https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php";

    public static Observations getObservations() throws JAXBException, MalformedURLException {
        JAXBContext context = JAXBContext.newInstance(Observations.class);
        Unmarshaller un = context.createUnmarshaller();
        Observations observations = (Observations) un.unmarshal(new URL(LINK));

        System.out.println(observations.getTimestamp());
        System.out.println(observations.getStations());

        return null;
    }
}
