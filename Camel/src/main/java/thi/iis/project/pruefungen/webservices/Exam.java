/**
 * Exam.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package thi.iis.project.pruefungen.webservices;

import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlRootElement;

import net.sf.json.JSONObject;

public class Exam  implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private java.util.Calendar date;

    private java.lang.String examId;

    private thi.iis.project.pruefungen.webservices.Professor professorId;

    private java.lang.String subject;

    public Exam() {
    }

    public Exam(
           java.util.Calendar date,
           java.lang.String examId,
           thi.iis.project.pruefungen.webservices.Professor professorId,
           java.lang.String subject) {
           this.date = date;
           this.examId = examId;
           this.professorId = professorId;
           this.subject = subject;
    }


    /**
     * Gets the date value for this Exam.
     * 
     * @return date
     */
    public java.util.Calendar getDate() {
        return date;
    }


    /**
     * Sets the date value for this Exam.
     * 
     * @param date
     */
    public void setDate(java.util.Calendar date) {
        this.date = date;
    }


    /**
     * Gets the examId value for this Exam.
     * 
     * @return examId
     */
    public java.lang.String getExamId() {
        return examId;
    }


    /**
     * Sets the examId value for this Exam.
     * 
     * @param examId
     */
    public void setExamId(java.lang.String examId) {
        this.examId = examId;
    }


    /**
     * Gets the professorId value for this Exam.
     * 
     * @return professorId
     */
    public thi.iis.project.pruefungen.webservices.Professor getProfessorId() {
        return professorId;
    }


    /**
     * Sets the professorId value for this Exam.
     * 
     * @param professorId
     */
    public void setProfessorId(thi.iis.project.pruefungen.webservices.Professor professorId) {
        this.professorId = professorId;
    }


    /**
     * Gets the subject value for this Exam.
     * 
     * @return subject
     */
    public java.lang.String getSubject() {
        return subject;
    }


    /**
     * Sets the subject value for this Exam.
     * 
     * @param subject
     */
    public void setSubject(java.lang.String subject) {
        this.subject = subject;
    }

    public JSONObject toJson(){
        JSONObject result = new JSONObject();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSZ");
        result.put("date", sdf.format(date.getTime()));
        result.put("examId", examId);
        result.put("subject", subject);
        result.put("examId", examId);
        result.put("professorId", professorId.toJson());
        result.put("subject", subject);
        
        return result;
    }

}
