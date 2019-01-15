package thi.iis.project.pruefungen.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the professor database table.
 * 
 * @author Katrin Krüger
 */
@Entity
@Table(name = "professor")
public class Professor implements Serializable {
    private static final long serialVersionUID = 1L;

    private String firstname;

    private String lastname;

    private String mail;

    @Id
    @Column(name = "professor_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int professorId;

    public Professor() {
    }

    public Professor(String firstname, String lastname, String mail) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getProfessorId() {
        return this.professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    @Override
    public String toString() {
        return "Professor [firstname=" + firstname + ", lastname=" + lastname + ", mail=" + mail + ", professorId="
                + professorId + "]";
    }

}