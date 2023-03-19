package ee.praktika.trial.task.models;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Fee object, which holds the:
 * rbf - regional base fee
 * atef - air temperature fee
 * wsef - wind speed fee
 * wpef - weather phenomenon fee
 * totalCost - total cost of all the fees
 */
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

    /**
     *
     * @return the totalCost of the fee
     */
    @Override
    public String toString() {
        return getTotalCost()+"€";
    }


}
