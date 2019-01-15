package thi.iis.project.pruefungen.jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the student_exam database table.
 * 
 * @author Katrin Krüger
 */
@Entity
@Table(name = "student_exam")
public class StudentExam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "document_uploaded")
    private Boolean documentUploaded;

    @JoinColumn(name = "exam_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Exam examId;

    private BigDecimal grade;

    @Column(name = "grade_checked")
    private Boolean gradeChecked;

    private Boolean participated;

    @JoinColumn(name = "registration_name")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Student registrationName;

    @Id
    @Column(name = "student_exam_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int studentExamId;

    public StudentExam() {
    }

    public StudentExam(Boolean documentUploaded, Exam examId, BigDecimal grade, Boolean gradeChecked,
            Boolean participated, Student registrationName) {
        super();
        this.documentUploaded = documentUploaded;
        this.examId = examId;
        this.grade = grade;
        this.gradeChecked = gradeChecked;
        this.participated = participated;
        this.registrationName = registrationName;
    }

    public Boolean getDocumentUploaded() {
        return this.documentUploaded;
    }

    public void setDocumentUploaded(Boolean documentUploaded) {
        this.documentUploaded = documentUploaded;
    }

    public Exam getExamId() {
        return this.examId;
    }

    public void setExamId(Exam examId) {
        this.examId = examId;
    }

    public BigDecimal getGrade() {
        return this.grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    public Boolean getGradeChecked() {
        return this.gradeChecked;
    }

    public void setGradeChecked(Boolean gradeChecked) {
        this.gradeChecked = gradeChecked;
    }

    public Boolean getParticipated() {
        return this.participated;
    }

    public void setParticipated(Boolean participated) {
        this.participated = participated;
    }

    public Student getRegistrationNumber() {
        return this.registrationName;
    }

    public void setRegistrationNumber(Student registrationNumber) {
        this.registrationName = registrationNumber;
    }

    public int getStudentExamId() {
        return this.studentExamId;
    }

    public void setStudentExamId(int studentExamId) {
        this.studentExamId = studentExamId;
    }

    @Override
    public String toString() {
        return "StudentExam [documentUploaded=" + documentUploaded + ", examId=" + examId + ", grade=" + grade
                + ", gradeChecked=" + gradeChecked + ", participated=" + participated + ", registrationNumber="
                + registrationName + ", studentExamId=" + studentExamId + "]";
    }

}