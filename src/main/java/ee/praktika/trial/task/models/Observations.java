package ee.praktika.trial.task.models;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "observations")
@XmlAccessorType(XmlAccessType.FIELD)
public class Observations {

    @XmlAttribute
    private String timestamp;

    //    @XmlElementWrapper(name = "observations")
//    @XmlElement(name = "station")
    private List<Station> station;

}
