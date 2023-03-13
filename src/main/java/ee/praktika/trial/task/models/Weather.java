package ee.praktika.trial.task.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
@Table(name="weather")
public class Weather {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer wmocode;

    @Column(nullable = false)
    private String phenomenon;

    @Column(nullable = false)
    private BigDecimal airTemperature;

    @Column(nullable = false)
    private BigDecimal windSpeed;

    @Column(nullable = false)
    private Date time;

}
