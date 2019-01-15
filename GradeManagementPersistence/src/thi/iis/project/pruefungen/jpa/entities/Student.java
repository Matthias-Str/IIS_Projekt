package thi.iis.project.pruefungen.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the student database table.
 * 
 * @author Katrin Krüger
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "course_of_study")
    private String courseOfStudy;

    private String firstname;

    private String lastname;

    private String mail;

    @Id
    @Column(name = "registration_name")
    private String registrationName;

    @Column(name = "registration_number")
    private int registrationNumber;

    public Student() {
    }

    public Student(String courseOfStudy, String firstname, String lastname, String mail, String registrationName) {
        super();
        this.courseOfStudy = courseOfStudy;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.registrationName = registrationName;
    }

    public String getCourseOfStudy() {
        return this.courseOfStudy;
    }

    public void setCourseOfStudy(String courseOfStudy) {
        this.courseOfStudy = courseOfStudy;
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

    public String getRegistrationName() {
        return registrationName;
    }

    public void setRegistrationName(String registrationName) {
        this.registrationName = registrationName;
    }

    public int getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString() {
        return "Student [courseOfStudy=" + courseOfStudy + ", firstname=" + firstname + ", lastname=" + lastname
                + ", mail=" + mail + ", registrationName=" + registrationName + ", registrationNumber="
                + registrationNumber + "]";
    }

}