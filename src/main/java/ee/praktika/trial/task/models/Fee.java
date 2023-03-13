package ee.praktika.trial.task.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@NoArgsConstructor
public class Fee {

    private double rbf = 0;
    private double atef = 0;
    private double wsef = 0;
    private double wpef = 0;
    private double totalCost = rbf+atef+wsef+wpef;


    @Override
    public String toString() {
        return totalCost+"€";
    }


}
