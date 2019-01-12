package thi.iis.project.pruefungen.beans;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import thi.iis.project.pruefungen.webservices.Exam;

@XmlRootElement
public class Anmeldung {
    String username;
    Map<String, Boolean> registrations;
    
    public Anmeldung(){
        
    }

    public Anmeldung(String username, Map<String, Boolean> registrations) {
        super();
        this.username = username;
        this.registrations = registrations;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, Boolean> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Map<String, Boolean> registrations) {
        this.registrations = registrations;
    }


    
    
}
