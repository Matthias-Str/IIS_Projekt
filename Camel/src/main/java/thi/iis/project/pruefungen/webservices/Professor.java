/**
 * Professor.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package thi.iis.project.pruefungen.webservices;

import net.sf.json.JSONObject;

public class Professor  implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private java.lang.String firstname;

    private java.lang.String lastname;

    private java.lang.String mail;

    private int professorId;

    public Professor() {
    }

    public Professor(
           java.lang.String firstname,
           java.lang.String lastname,
           java.lang.String mail,
           int professorId) {
           this.firstname = firstname;
           this.lastname = lastname;
           this.mail = mail;
           this.professorId = professorId;
    }


    /**
     * Gets the firstname value for this Professor.
     * 
     * @return firstname
     */
    public java.lang.String getFirstname() {
        return firstname;
    }


    /**
     * Sets the firstname value for this Professor.
     * 
     * @param firstname
     */
    public void setFirstname(java.lang.String firstname) {
        this.firstname = firstname;
    }


    /**
     * Gets the lastname value for this Professor.
     * 
     * @return lastname
     */
    public java.lang.String getLastname() {
        return lastname;
    }


    /**
     * Sets the lastname value for this Professor.
     * 
     * @param lastname
     */
    public void setLastname(java.lang.String lastname) {
        this.lastname = lastname;
    }


    /**
     * Gets the mail value for this Professor.
     * 
     * @return mail
     */
    public java.lang.String getMail() {
        return mail;
    }


    /**
     * Sets the mail value for this Professor.
     * 
     * @param mail
     */
    public void setMail(java.lang.String mail) {
        this.mail = mail;
    }


    /**
     * Gets the professorId value for this Professor.
     * 
     * @return professorId
     */
    public int getProfessorId() {
        return professorId;
    }


    /**
     * Sets the professorId value for this Professor.
     * 
     * @param professorId
     */
    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public JSONObject toJson(){
        JSONObject result = new JSONObject();

        result.put("firstname", firstname);
        result.put("lastname", lastname);
        result.put("mail", mail);
        result.put("professorId", professorId);
        
        return result;
    }

}
