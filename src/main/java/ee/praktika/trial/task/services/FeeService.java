package ee.praktika.trial.task.services;

import ee.praktika.trial.task.models.Fee;

public interface FeeService {

    public Fee calculateCost(String city, String vehicle);
}
