package thi.iis.project.pruefungen.pojos;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * DataType for Message transfer of date with name that needs to be persisted
 * 
 * @author Katrin Krüger
 *
 */
@XmlRootElement(name = "entry")
public class CurrentDate {
    String name;
    String date;

    public CurrentDate() {

    }

    @XmlElement(name = "key")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "value")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}