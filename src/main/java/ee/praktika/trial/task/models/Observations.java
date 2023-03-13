package ee.praktika.trial.task.models;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "observations")
@XmlAccessorType(XmlAccessType.FIELD)
public class Observations {

    @XmlAttribute
    private String timestamp;

    @XmlElement(name = "station")
    private List<Station> stations;

}
