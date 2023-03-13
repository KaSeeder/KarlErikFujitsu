package ee.praktika.trial.task.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Fee {

    private BigDecimal rbf = BigDecimal.valueOf(0);
    private BigDecimal atef = BigDecimal.valueOf(0);
    private BigDecimal wsef = BigDecimal.valueOf(0);
    private BigDecimal wpef = BigDecimal.valueOf(0);
    private BigDecimal totalCost = BigDecimal.valueOf(0);


}
