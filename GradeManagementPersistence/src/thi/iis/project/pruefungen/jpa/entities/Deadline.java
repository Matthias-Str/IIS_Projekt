package thi.iis.project.pruefungen.jpa.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the deadline database table.
 * @author Katrin Krüger
 */
@Entity
@Table(name="deadline")
@NamedQuery(name="Deadline.findAll", query="SELECT d FROM Deadline d")
public class Deadline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date date;

	@Id
	@Column(name="deadline_name")
	private String deadlineName;

	public Deadline() {
	}

	
	public Deadline(Date date, String deadlineName) {
        super();
        this.date = date;
        this.deadlineName = deadlineName;
    }


    public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDeadlineName() {
		return this.deadlineName;
	}

	public void setDeadlineName(String deadlineName) {
		this.deadlineName = deadlineName;
	}


    @Override
    public String toString() {
        return "Deadline [date=" + date + ", deadlineName=" + deadlineName + "]";
    }
	
	

}