package ee.praktika.trial.task.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.math.BigDecimal;


@Data
@RequiredArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Station {
    private String name;
    private String wmocode;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String phenomenon;
    private BigDecimal visibility;
    private Integer precipitations;
    private BigDecimal airpressure;
    private Integer relativehumidity;
    private BigDecimal airtemperature;
    private Integer winddirection;
    private BigDecimal windspeed;
    private BigDecimal windspeedmax;
    private BigDecimal waterlevel;
    private BigDecimal waterlevel_eh2000;
    private BigDecimal watertemperature;
    private BigDecimal uvindex;

}
