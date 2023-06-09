package ee.praktika.trial.task.models;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 * Observation Object, which takes information from the XML file.
 */
@Data
@XmlRootElement(name = "observations")
@XmlAccessorType(XmlAccessType.FIELD)
public class Observations {

    @XmlAttribute(name="timestamp")
    private Date time;

    @XmlElement(name = "station")
    private List<Station> stations;

}
