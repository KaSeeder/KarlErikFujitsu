package ee.praktika.trial.task.models;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * Station object, which takes information from the XML file.
 */
@Data
@XmlRootElement(name = "station")
public class Station {
    private String name;
    private Integer wmocode;
    private Float longitude;
    private Float latitude;
    private String phenomenon;
    private Float visibility;
    private Integer precipitations;
    private Float airpressure;
    private Integer relativehumidity;
    private Float airtemperature;
    private Integer winddirection;
    private Float windspeed;
    private Float windspeedmax;
    private Float waterlevel;
    private Float waterlevel_eh2000;
    private Float watertemperature;
    private Float uvindex;

}
