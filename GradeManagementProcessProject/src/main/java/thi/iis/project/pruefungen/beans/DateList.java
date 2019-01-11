package thi.iis.project.pruefungen.beans;

import java.util.Date;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace="http://tutego.com/")
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
