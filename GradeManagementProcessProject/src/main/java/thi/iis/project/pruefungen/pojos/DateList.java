package thi.iis.project.pruefungen.pojos;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class represent list of dates that user inserts in task "Termine festlegen"
 * 
 * @author Katrin Krüger
 *
 */
@XmlRootElement()
public class DateList {

    private Map<String, String> dateList;

    public DateList() {
        super();
    }

    public DateList(Map<String, String> dateList) {
        super();
        this.dateList = dateList;
    }

    public Map<String, String> getDateList() {
        return dateList;
    }

    public void setDateList(Map<String, String> dateList) {
        this.dateList = dateList;
    }

}
