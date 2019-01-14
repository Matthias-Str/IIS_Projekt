/**
 * StudentExam.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package thi.iis.project.pruefungen.webservices;

public class StudentExam  implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private java.lang.Boolean documentUploaded;

    private thi.iis.project.pruefungen.webservices.Exam examId;

    private java.math.BigDecimal grade;

    private java.lang.Boolean gradeChecked;

    private java.lang.Boolean participated;

    private thi.iis.project.pruefungen.webservices.Student registrationNumber;

    private int studentExamId;

    public StudentExam() {
    }

    public StudentExam(
           java.lang.Boolean documentUploaded,
           thi.iis.project.pruefungen.webservices.Exam examId,
           java.math.BigDecimal grade,
           java.lang.Boolean gradeChecked,
           java.lang.Boolean participated,
           thi.iis.project.pruefungen.webservices.Student registrationNumber,
           int studentExamId) {
           this.documentUploaded = documentUploaded;
           this.examId = examId;
           this.grade = grade;
           this.gradeChecked = gradeChecked;
           this.participated = participated;
           this.registrationNumber = registrationNumber;
           this.studentExamId = studentExamId;
    }


    /**
     * Gets the documentUploaded value for this StudentExam.
     * 
     * @return documentUploaded
     */
    public java.lang.Boolean getDocumentUploaded() {
        return documentUploaded;
    }


    /**
     * Sets the documentUploaded value for this StudentExam.
     * 
     * @param documentUploaded
     */
    public void setDocumentUploaded(java.lang.Boolean documentUploaded) {
        this.documentUploaded = documentUploaded;
    }


    /**
     * Gets the examId value for this StudentExam.
     * 
     * @return examId
     */
    public thi.iis.project.pruefungen.webservices.Exam getExamId() {
        return examId;
    }


    /**
     * Sets the examId value for this StudentExam.
     * 
     * @param examId
     */
    public void setExamId(thi.iis.project.pruefungen.webservices.Exam examId) {
        this.examId = examId;
    }


    /**
     * Gets the grade value for this StudentExam.
     * 
     * @return grade
     */
    public java.math.BigDecimal getGrade() {
        return grade;
    }


    /**
     * Sets the grade value for this StudentExam.
     * 
     * @param grade
     */
    public void setGrade(java.math.BigDecimal grade) {
        this.grade = grade;
    }


    /**
     * Gets the gradeChecked value for this StudentExam.
     * 
     * @return gradeChecked
     */
    public java.lang.Boolean getGradeChecked() {
        return gradeChecked;
    }


    /**
     * Sets the gradeChecked value for this StudentExam.
     * 
     * @param gradeChecked
     */
    public void setGradeChecked(java.lang.Boolean gradeChecked) {
        this.gradeChecked = gradeChecked;
    }


    /**
     * Gets the participated value for this StudentExam.
     * 
     * @return participated
     */
    public java.lang.Boolean getParticipated() {
        return participated;
    }


    /**
     * Sets the participated value for this StudentExam.
     * 
     * @param participated
     */
    public void setParticipated(java.lang.Boolean participated) {
        this.participated = participated;
    }


    /**
     * Gets the registrationNumber value for this StudentExam.
     * 
     * @return registrationNumber
     */
    public thi.iis.project.pruefungen.webservices.Student getRegistrationNumber() {
        return registrationNumber;
    }


    /**
     * Sets the registrationNumber value for this StudentExam.
     * 
     * @param registrationNumber
     */
    public void setRegistrationNumber(thi.iis.project.pruefungen.webservices.Student registrationNumber) {
        this.registrationNumber = registrationNumber;
    }


    /**
     * Gets the studentExamId value for this StudentExam.
     * 
     * @return studentExamId
     */
    public int getStudentExamId() {
        return studentExamId;
    }


    /**
     * Sets the studentExamId value for this StudentExam.
     * 
     * @param studentExamId
     */
    public void setStudentExamId(int studentExamId) {
        this.studentExamId = studentExamId;
    }



}
