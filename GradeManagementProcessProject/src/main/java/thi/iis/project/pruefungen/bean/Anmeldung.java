package thi.iis.project.pruefungen.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Anmeldung implements java.io.Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    String username;
    Boolean sesa;
    Boolean kao;
    Boolean iis;
    Boolean itim;
    
    public Anmeldung(){
        
    }
    
    public Anmeldung(String username, Boolean sesa, Boolean kao, Boolean iis, Boolean itim){
        this.username = username;
        this.sesa = sesa;
        this.kao = kao;
        this.iis = iis;
        this.itim = itim;
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getSesa() {
        return sesa;
    }

    public void setSesa(Boolean sesa) {
        this.sesa = sesa;
    }

    public Boolean getKao() {
        return kao;
    }

    public void setKao(Boolean kao) {
        this.kao = kao;
    }

    public Boolean getIis() {
        return iis;
    }

    public void setIis(Boolean iis) {
        this.iis = iis;
    }

    public Boolean getItim() {
        return itim;
    }

    public void setItim(Boolean itim) {
        this.itim = itim;
    }

    @Override
    public String toString() {
        return "Anmeldung [username=" + username + ", sesa=" + sesa + ", kao=" + kao + ", iis=" + iis + ", itim=" + itim
                + "]";
    }
    
    
}
