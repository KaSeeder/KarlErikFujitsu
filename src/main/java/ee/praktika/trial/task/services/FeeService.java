package ee.praktika.trial.task.services;

import ee.praktika.trial.task.models.Fee;
import ee.praktika.trial.task.models.Weather;

import java.util.List;

/**
 * Fee service interface
 */
public interface FeeService {

    public Fee calculateCost(String city, String vehicle);
    public String renameCity(String city);

    public void calculateFees(String city, String vehicle, List<Weather> info, Fee fee);
    public void calculateAtef(List<Weather> info, Fee fee);
    public void calculateWsef(List<Weather> info, Fee fee, String vehicle);
    public void calculateWpef(List<Weather> info, Fee fee);


}
