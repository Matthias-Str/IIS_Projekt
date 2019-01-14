/**
 * Deadline.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package thi.iis.project.pruefungen.webservices;

public class Deadline  implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private java.util.Calendar date;

    private java.lang.String deadlineName;

    public Deadline() {
    }

    public Deadline(
           java.util.Calendar date,
           java.lang.String deadlineName) {
           this.date = date;
           this.deadlineName = deadlineName;
    }


    /**
     * Gets the date value for this Deadline.
     * 
     * @return date
     */
    public java.util.Calendar getDate() {
        return date;
    }


    /**
     * Sets the date value for this Deadline.
     * 
     * @param date
     */
    public void setDate(java.util.Calendar date) {
        this.date = date;
    }


    /**
     * Gets the deadlineName value for this Deadline.
     * 
     * @return deadlineName
     */
    public java.lang.String getDeadlineName() {
        return deadlineName;
    }


    /**
     * Sets the deadlineName value for this Deadline.
     * 
     * @param deadlineName
     */
    public void setDeadlineName(java.lang.String deadlineName) {
        this.deadlineName = deadlineName;
    }



}
