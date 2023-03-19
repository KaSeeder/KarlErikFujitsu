package ee.praktika.trial.task.models;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Fee {

    private double rbf = 0;
    private double atef = 0;
    private double wsef = 0;
    private double wpef = 0;
    private double totalCost = 0;

    /**
     *
     * @return the total cost of all the different fees.
     */
    public double getTotalCost() {
        this.totalCost = rbf+atef+wsef+wpef;
        return totalCost;
    }

    @Override
    public String toString() {
        return getTotalCost()+"€";
    }


}
