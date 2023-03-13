package ee.praktika.trial.task.services.impl;

import ee.praktika.trial.task.models.Observations;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Collections;
import java.util.List;


/**
 * New and updated version of Impl.
 */
public class WeatherAPIServiceImpl {

    private static final String link = "https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php";
    private static final RestTemplate restTemplate = new RestTemplate();

    static {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.xml();
        restTemplate.setMessageConverters(Collections.singletonList(new MappingJackson2HttpMessageConverter(builder.build())));

        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory());
//        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
//        messageConverters.add(new Jaxb2RootElementHttpMessageConverter());
//        restTemplate.setMessageConverters(messageConverters);
    }

    public static Observations getObservations() {
        Observations response = restTemplate.getForObject(link, Observations.class);
        System.out.println(response.getStations());
        return null;
    }
}
