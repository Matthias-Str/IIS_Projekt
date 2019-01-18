package thi.iis.project.pruefungen.pojos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PruefungsStatistik {
    private long mean;
    private long avarage;
    
    public PruefungsStatistik() {
    }
    
    public PruefungsStatistik(long mean, long avarage) {
        this.mean = mean;
        this.avarage = avarage;
    }
    public long getMean() {
        return mean;
    }
    public void setMean(long mean) {
        this.mean = mean;
    }
    public long getAvarage() {
        return avarage;
    }
    public void setAvarage(long avarage) {
        this.avarage = avarage;
    }
}
