/**
 * Student.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package thi.iis.project.pruefungen.webservices;

import net.sf.json.JSONObject;

public class Student  implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private java.lang.String courseOfStudy;

    private java.lang.String firstname;

    private java.lang.String lastname;

    private java.lang.String mail;

    private java.lang.String registrationName;

    private int registrationNumber;

    public Student() {
    }

    public Student(
           java.lang.String courseOfStudy,
           java.lang.String firstname,
           java.lang.String lastname,
           java.lang.String mail,
           java.lang.String registrationName,
           int registrationNumber) {
           this.courseOfStudy = courseOfStudy;
           this.firstname = firstname;
           this.lastname = lastname;
           this.mail = mail;
           this.registrationName = registrationName;
           this.registrationNumber = registrationNumber;
    }


    /**
     * Gets the courseOfStudy value for this Student.
     * 
     * @return courseOfStudy
     */
    public java.lang.String getCourseOfStudy() {
        return courseOfStudy;
    }


    /**
     * Sets the courseOfStudy value for this Student.
     * 
     * @param courseOfStudy
     */
    public void setCourseOfStudy(java.lang.String courseOfStudy) {
        this.courseOfStudy = courseOfStudy;
    }


    /**
     * Gets the firstname value for this Student.
     * 
     * @return firstname
     */
    public java.lang.String getFirstname() {
        return firstname;
    }


    /**
     * Sets the firstname value for this Student.
     * 
     * @param firstname
     */
    public void setFirstname(java.lang.String firstname) {
        this.firstname = firstname;
    }


    /**
     * Gets the lastname value for this Student.
     * 
     * @return lastname
     */
    public java.lang.String getLastname() {
        return lastname;
    }


    /**
     * Sets the lastname value for this Student.
     * 
     * @param lastname
     */
    public void setLastname(java.lang.String lastname) {
        this.lastname = lastname;
    }


    /**
     * Gets the mail value for this Student.
     * 
     * @return mail
     */
    public java.lang.String getMail() {
        return mail;
    }


    /**
     * Sets the mail value for this Student.
     * 
     * @param mail
     */
    public void setMail(java.lang.String mail) {
        this.mail = mail;
    }


    /**
     * Gets the registrationName value for this Student.
     * 
     * @return registrationName
     */
    public java.lang.String getRegistrationName() {
        return registrationName;
    }


    /**
     * Sets the registrationName value for this Student.
     * 
     * @param registrationName
     */
    public void setRegistrationName(java.lang.String registrationName) {
        this.registrationName = registrationName;
    }


    /**
     * Gets the registrationNumber value for this Student.
     * 
     * @return registrationNumber
     */
    public int getRegistrationNumber() {
        return registrationNumber;
    }


    /**
     * Sets the registrationNumber value for this Student.
     * 
     * @param registrationNumber
     */
    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public JSONObject toJson(){
        JSONObject result = new JSONObject();
        
        result.put("courseOfStudy", courseOfStudy);
        result.put("firstname", firstname);
        result.put("lastname", lastname);
        result.put("mail", mail);
        result.put("registrationName", registrationName);
        result.put("registrationNumber", registrationNumber);
        
        return result;
    }

}
