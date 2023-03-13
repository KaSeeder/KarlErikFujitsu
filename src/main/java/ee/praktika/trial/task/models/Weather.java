package ee.praktika.trial.task.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name="weather")
@NoArgsConstructor
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

    private Float airTemperature;

    private Float windSpeed;

    @Column(nullable = false)
    private Date time;

    public Weather(Station station, Date time) {
        this.name = station.getName();
        this.wmocode = station.getWmocode();
        this.phenomenon = station.getPhenomenon();
        this.airTemperature = station.getAirtemperature();
        this.windSpeed = station.getWindspeed();
        this.time = time;
    }
}
