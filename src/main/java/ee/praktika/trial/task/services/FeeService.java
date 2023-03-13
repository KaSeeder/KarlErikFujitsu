package ee.praktika.trial.task.services;

import ee.praktika.trial.task.models.Fee;
import ee.praktika.trial.task.models.Weather;

import java.util.List;

public interface FeeService {

    public Fee calculateCost(String city, String vehicle);
    public void renameCity(String city);

    public void calculateFees(String city, String vehicle, List<Weather> info);
    public void calculateAtef(List<Weather> info);
    public void calculateWsef(List<Weather> info);
    public void calculateWpef(List<Weather> info);


}
